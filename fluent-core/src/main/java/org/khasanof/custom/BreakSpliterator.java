package org.khasanof.custom;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author Nurislom
 * @see org.khasanof.executors
 * @since 05.07.2023 0:05
 */
public class BreakSpliterator<T> extends Spliterators.AbstractSpliterator<T> {

    private Spliterator<T> spliterator;
    private Predicate<T> predicate;
    private boolean isMatched = true;

    /**
     * Creates a spliterator reporting the given estimated size and
     * additionalCharacteristics.
     *
     * @param est                       the estimated size of this spliterator if known, otherwise
     *                                  {@code Long.MAX_VALUE}.
     * @param additionalCharacteristics properties of this spliterator's
     *                                  source or elements.  If {@code SIZED} is reported then this
     *                                  spliterator will additionally report {@code SUBSIZED}.
     */
    protected BreakSpliterator(long est, int additionalCharacteristics) {
        super(est, additionalCharacteristics);
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
        boolean hadNext = spliterator.tryAdvance(elem -> {
            if (predicate.test(elem) && isMatched) {
                action.accept(elem);
            } else {
                isMatched = false;
            }
        });
        return hadNext && isMatched;
    }
}
