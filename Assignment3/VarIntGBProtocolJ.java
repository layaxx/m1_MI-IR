package de.uniba.minf.ir.encoding.student;

import de.uniba.minf.ir.encoding.toolkit.*;
import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;

import java.io.NotActiveException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VarIntGBProtocolJ implements Protocol {

    /*
     * Encodes an [IntSource] in chunks of size 4.
     * The layout of the bytes is as follows:
     * +----------------------------------+
     * | Meta | Int1 | Int2 | Int3 | Int4 |
     * +----------------------------------+
     *
     * The bytes for each Int are stored in big endian format.
     * Meaning that a int value like this:
     *  0x0A_0B_0C_0D
     * will be stored like this in a byte format:
     *  [0A, 0B, 0C, 0D]
     *
     * Otherwise the unit tests will fail.
     */

    private final static int CHUNK_SIZE = 4;
    private final static int NUMBER_BITS = 8;

    private final static int EIGHT_ONES = 255;
    private final static int EIGHT_ZEROS = ~EIGHT_ONES;

    private final static int[] BIT_PAIRS = {192, 48, 12, 3};

    @Override
    public void decode(@NotNull ByteSource src, @NotNull IntSink dest) {
        Objects.requireNonNull(src);
        Objects.requireNonNull(dest);

        while(src.hasMoreValues()){
            byte meta = src.get();
            int[] metaAsInt = getMetaData(meta);

            for(int i = 0; i < metaAsInt.length && src.hasMoreValues(); i++){
                List<Byte> values = new ArrayList<>();
                for(int j = 0; j < metaAsInt[i]; j++){
                    values.add(src.get());
                }
                int result = decodeSingle(values);
                dest.write(result);
            }
        }
    }

    private int decodeSingle(List<Byte> bytes){
        int result = 0;
        int shift = 0;
        for(int i = bytes.size() - 1; i >= 0; i--){
            int byteValue = bytes.get(i) & EIGHT_ONES;
            result = result | (byteValue << shift);
            shift += 8; 
        }

        return result;
    }

    private int[] getMetaData(byte meta){
        int[] result = new int[CHUNK_SIZE];

        int counter = 0;
        int shift = 6;
        for(int value : BIT_PAIRS){
            result[counter] = ((meta & value) >> shift) + 1;
            counter++;
            shift -= 2;
        }

        return result;
    }

    @Override
    public void encode(@NotNull IntSource src, @NotNull ByteSink dest) {
        Objects.requireNonNull(src);
        Objects.requireNonNull(dest);

        int numCounter = CHUNK_SIZE;
        byte meta = 0x0;

        List<Byte> group = new ArrayList<>();

        for(int i : src){
            if (numCounter == 0){
                dest.write(meta);
                for(byte value : group){
                    dest.write(value);
                }
                group.clear();
                meta = 0x0;
                numCounter = CHUNK_SIZE;
            }

            int byteSize = getSize(i);
            byte[] codedValue = encodeSingle(i, byteSize);
            for(byte code : codedValue){
                group.add(code);
            }
            meta = changeMeta(meta, byteSize, numCounter - 1);

            numCounter--;
        }

        dest.write(meta);
        for(byte value : group){
            dest.write(value);
        }

    }

    private byte[] encodeSingle(int value, int size){
        byte[] result = new byte[size];

        while(value != 0){
            result[size - 1] = (byte) (value & EIGHT_ONES);
            value = value >>> NUMBER_BITS;
            size--;
        }

        return result;
    }

    private int getSize(int value){
        int counter = 0;


        while((value & EIGHT_ZEROS) != 0){
            counter++;
            value = value >>> NUMBER_BITS;
        }

        return counter + 1;
    }

    private byte changeMeta(byte meta, int number, int position){
        byte value = (byte) ((number - 1) & 0x3);
        value = (byte) (value << position * 2);
        return (byte) (meta | value);
    }
}
