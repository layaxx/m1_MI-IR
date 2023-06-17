package de.uniba.minf.ir.toEdit.dgap;

import de.uniba.minf.ir.toolkit.dgap.*;
import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class DGapSearcherJ implements DGapSearcher {

    // Do not edit the singleton pattern
    // We do not need any double locking etc. we know what'll happen with it.
    public final static DGapSearcherJ INSTANCE = new DGapSearcherJ();

    // Do not change the constructor
    private DGapSearcherJ() {
    }

    @NotNull
    @Override
    public DGapResult findViaDGap(
            long toFind,
            @NotNull DGapIndex dGapIndex
    ) throws ValueNotInIndexException {
        Objects.requireNonNull(dGapIndex);


        // Step 1: find skipPointer to use
        Iterator<SkipPointer> iterator = dGapIndex.skipPointers().iterator();
        SkipPointer skipPointer = iterator.next();
        SkipPointer previous = SkipPointer.ZERO;
        while (toFind > skipPointer.getDocumentNumber() && iterator.hasNext()) {
            previous = skipPointer;
            skipPointer = iterator.next();
        }
        skipPointer = previous;

        int index  = skipPointer.getIndex();
        long dokID  = skipPointer.getDocumentNumber();

        // Skip n Elements according to skipPointer
        Iterable<Integer> iterable = () -> dGapIndex.dGaps();
        Stream<Integer> targetStream = StreamSupport.stream(iterable.spliterator(), false);
        Iterator<Integer> newIterator = targetStream.skip(index).iterator();

        // Find DokID
        while (newIterator.hasNext()) {
            dokID += newIterator.next();
            if (dokID == toFind) return new DGapResult(toFind, skipPointer, index);
            index++;
        }

        // Fallback: Value was not found
        throw new ValueNotInIndexException("fallthrough");
    }
}
