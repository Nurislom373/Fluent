package org.khasanof.executors.matcher;

import org.khasanof.executors.expression.ExpressionMatcher;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * Author: Nurislom
 * <br/>
 * Date: 20.06.2023
 * <br/>
 * Time: 21:59
 * <br/>
 * Package: org.khasanof.core.executors.matcher
 */
@SuppressWarnings({"rawtypes"})
public abstract class GenericMatcher<T extends Annotation, V> extends AbstractMatcher {

    public GenericMatcher(Map<String, ExpressionMatcher> expressionMatcherMap) {
        super(expressionMatcherMap);
    }

    public abstract boolean matcher(T annotation, V value);

    public abstract Class<T> getType();

}
