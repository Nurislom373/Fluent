package org.khasanof.service.handler.decorator.impls;

import org.khasanof.executors.matcher.GenericMatcher;
import org.khasanof.feature.annotation.HandlerAnnotationRegistry;
import org.khasanof.service.FindBeansOfTypeService;
import org.khasanof.service.handler.decorator.BaseHandlerAnnotationDecorator;
import org.springframework.context.ApplicationContext;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.service.handler.decorator.impls
 * @since 2/20/2024 10:22 PM
 */
@SuppressWarnings({"rawtypes"})
public class MatcherHandlerAnnotationDecorator extends BaseHandlerAnnotationDecorator {

    private final FindBeansOfTypeService findBeansOfTypeService;

    public MatcherHandlerAnnotationDecorator(FindBeansOfTypeService findBeansOfTypeService) {
        this.findBeansOfTypeService = findBeansOfTypeService;
    }

    @Override
    public void execute(HandlerAnnotationRegistry registry) {
        super.execute(registry);
        internalExecute(registry);
    }

    private void internalExecute(HandlerAnnotationRegistry registry) {
        if (Objects.isNull(registry.getMatcher())) {
            return;
        }
        GenericMatcher matcher = getMatcher(registry);
        if (!Objects.equals(matcher.getType(), registry.getAnnotation())) {
            throw new RuntimeException("Matcher type not match annotation!");
        }
    }

    private GenericMatcher getMatcher(HandlerAnnotationRegistry registry) {
        return findBeansOfTypeService.findBean(registry.getMatcher())
                .orElseThrow(() -> new RuntimeException("Matcher instance not found!"));
    }
}
