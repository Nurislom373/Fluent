package org.khasanof.models.matcher.function;

import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author Nurislom
 * @see org.khasanof.models.matcher
 * @since 3/2/2024 4:05 PM
 */
public interface RepeatableMatcherFunction extends BiFunction<Stream, Predicate, Boolean> {
}
