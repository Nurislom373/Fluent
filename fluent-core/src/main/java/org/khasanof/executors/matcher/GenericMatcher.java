package org.khasanof.executors.matcher;

import org.khasanof.service.expression.ExpressionMatcherService;

import java.lang.annotation.Annotation;

import static org.khasanof.utils.BaseUtils.getFirstGenericType;

/**
 * Author: Nurislom
 * <br/>
 * Date: 20.06.2023
 * <br/>
 * Time: 21:59
 * <br/>
 * Package: org.khasanof.core.executors.matcher
 */
public abstract class GenericMatcher<T extends Annotation, V> {

    protected final ExpressionMatcherService expressionMatcherService;

    public GenericMatcher(ExpressionMatcherService expressionMatcherService) {
        this.expressionMatcherService = expressionMatcherService;
    }

    public abstract boolean matcher(T annotation, V value);

    public Class<T> getType() {
        return getFirstGenericType(getClass());
    }
}
