package org.khasanof.executors.matcher;

import org.khasanof.service.expression.MultiExpressionMatcherService;

import java.lang.annotation.Annotation;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 07.07.2023 22:37
 */
public abstract class MultiGenericMatcher<T extends Annotation, V> implements AbstractMatcher<T, V> {

    protected final GenericMatcher matcher;

    protected final MultiExpressionMatcherService expressionMatcherService;

    protected MultiGenericMatcher(GenericMatcher matcher, MultiExpressionMatcherService expressionMatcherService) {
        this.matcher = matcher;
        this.expressionMatcherService = expressionMatcherService;
    }
}
