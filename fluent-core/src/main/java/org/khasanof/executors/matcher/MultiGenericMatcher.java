package org.khasanof.executors.matcher;

import org.khasanof.enums.RepeatableMatchType;
import org.khasanof.executors.expression.ExpressionMatcher;

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
public abstract class MultiGenericMatcher<T extends Annotation, S extends Annotation, V> extends GenericMatcher<T, V> {

    protected final GenericMatcher matcher;

    protected final Map<RepeatableMatchType, BiFunction<Stream<S>, Predicate<S>, Boolean>>
            multiMatchScopeFunctionMap = new HashMap<>();

    protected MultiGenericMatcher(GenericMatcher matcher, Map<String, ExpressionMatcher> expressionMatcherMap) {
        super(expressionMatcherMap);
        this.matcher = matcher;
        setMultiMatchScopeFunctionMap();
    }

    void setMultiMatchScopeFunctionMap() {
        multiMatchScopeFunctionMap.put(RepeatableMatchType.ANY_MATCH, Stream::anyMatch);
        multiMatchScopeFunctionMap.put(RepeatableMatchType.ALL_MATCH, Stream::allMatch);
        multiMatchScopeFunctionMap.put(RepeatableMatchType.NONE_MATCH, Stream::noneMatch);
    }
}
