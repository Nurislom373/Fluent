package org.khasanof.custom;

import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author Nurislom
 * @see org.khasanof.custom
 * @since 05.07.2023 0:09
 */
public class BreakerForEach {

    public static class Breaker {

        private boolean shouldBreak = false;

        public void stop() {
            shouldBreak = true;
        }

        boolean get() {
            return shouldBreak;
        }
    }

    public static <T> void forEach(Stream<T> stream, BiConsumer<T, Breaker> consumer, Runnable runnable) {
        forEach(stream, consumer);
        runnable.run();
    }

    public static <T> void forEach(Stream<T> stream, BiConsumer<T, Breaker> consumer, Predicate<T> predicate,
                                   Runnable runnable) {
        forEach(stream.filter(predicate), consumer);
        runnable.run();
    }

    public static <T> void forEach(Stream<T> stream, BiConsumer<T, Breaker> consumer) {
        Spliterator<T> spliterator = stream.spliterator();
        boolean hadNext = true;
        Breaker breaker = new Breaker();

        while (hadNext && !breaker.get()) {
            hadNext = spliterator.tryAdvance(elem -> {
                consumer.accept(elem, breaker);
            });
        }
    }
}
