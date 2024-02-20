package org.khasanof.service.handler.decorator.impls;

import org.khasanof.executors.matcher.GenericMatcher;
import org.khasanof.feature.annotation.HandlerAnnotationRegistry;
import org.khasanof.service.handler.decorator.BaseHandlerAnnotationDecorator;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.service.handler.decorator.impls
 * @since 2/20/2024 10:22 PM
 */
@SuppressWarnings({"rawtypes"})
public class MatcherHandlerAnnotationDecorator extends BaseHandlerAnnotationDecorator {

    @Override
    public void execute(HandlerAnnotationRegistry registry) {
        super.execute(registry);
        internalExecute(registry);
    }

    private void internalExecute(HandlerAnnotationRegistry registry) {
        GenericMatcher matcher = registry.getMatcher();

        if (!Objects.equals(matcher.getType(), registry.getAnnotation())) {
            throw new RuntimeException("Matcher type not match annotation!");
        }
    }
}
