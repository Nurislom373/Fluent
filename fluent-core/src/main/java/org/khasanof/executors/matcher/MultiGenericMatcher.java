package org.khasanof.executors.matcher;

import org.khasanof.enums.RepeatableMatchType;
import org.khasanof.executors.expression.ExpressionMatcher;
import org.khasanof.service.expression.ExpressionMatcherService;
import org.khasanof.service.expression.MultiExpressionMatcherService;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 07.07.2023 22:37
 */
public abstract class MultiGenericMatcher<T extends Annotation, S extends Annotation, V> {

    protected final GenericMatcher matcher;
    protected final MultiExpressionMatcherService expressionMatcherService;
    protected final Map<RepeatableMatchType, BiFunction<Stream<S>, Predicate<S>, Boolean>> multiMatchScopeFunctionMap = new HashMap<>();

    protected MultiGenericMatcher(GenericMatcher matcher, MultiExpressionMatcherService expressionMatcherService) {
        this.matcher = matcher;
        this.expressionMatcherService = expressionMatcherService;
    }

    public abstract boolean matcher(T annotation, V value);
}
