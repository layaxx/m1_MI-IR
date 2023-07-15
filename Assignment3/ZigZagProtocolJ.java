package de.uniba.minf.ir.encoding.student;

import de.uniba.minf.ir.encoding.example.VByteProtocolJ;
import de.uniba.minf.ir.encoding.toolkit.*;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ZigZagProtocolJ implements Protocol {

    /*
     * In the context of information retrieval, zigzag encoding is used synonymously for
     * the method of zigzagging numbers (as described in the supplemented documents) and
     * the chaining of zigzag and v-byte.
     *
     * Therefore, this protocol requires you to combine zigzagging with v-byte.
     *
     * Hint: Look at the VByteProtocolJ, maybe you can reuse something?
     */


    @Override
    public void encode(@NotNull IntSource src, @NotNull ByteSink dest) {
        Objects.requireNonNull(src);
        Objects.requireNonNull(dest);

        src.iterator().forEachRemaining(current ->
                VByteProtocolJ.encodeSingle(zigzag(current), dest)
        );
    }

    private int zigzag(int src) {
        return (src >> 31) ^ (src << 1);
    }

    private int unzigzag(int src) {
        return (src >>> 1) ^ -(src & 1);
    }

    @Override
    public void decode(@NotNull ByteSource src, @NotNull IntSink dest) {
        Objects.requireNonNull(src);
        Objects.requireNonNull(dest);

        IntSink ints = new HeapIntSink();
        new VByteProtocolJ().decode(src, ints);

        ints.toSource().iterator().forEachRemaining(in -> dest.write(unzigzag(in)));
    }
}
