package org.khasanof.service.handler.decorator.impls;

import org.khasanof.collector.method.checker.HandleMethodChecker;
import org.khasanof.collector.method.checker.HandleMethodCheckerMediator;
import org.khasanof.feature.annotation.HandlerAnnotationRegistry;
import org.khasanof.service.FindBeansOfTypeService;
import org.khasanof.service.handler.decorator.BaseHandlerAnnotationDecorator;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.service.handler.decorator.impls
 * @since 2/20/2024 10:53 PM
 */
public class CheckerHandlerAnnotationDecorator extends BaseHandlerAnnotationDecorator {

    private final FindBeansOfTypeService findBeansOfTypeService;
    private final HandleMethodCheckerMediator handleMethodCheckerMediator;

    public CheckerHandlerAnnotationDecorator(FindBeansOfTypeService findBeansOfTypeService,
                                             HandleMethodCheckerMediator handleMethodCheckerMediator) {

        this.findBeansOfTypeService = findBeansOfTypeService;
        this.handleMethodCheckerMediator = handleMethodCheckerMediator;
    }

    @Override
    public void execute(HandlerAnnotationRegistry registry) {
        super.execute(registry);
        internalExecute(registry);
    }

    private void internalExecute(HandlerAnnotationRegistry registry) {
        if (Objects.isNull(registry.getHandleMethodChecker())) {
            return;
        }
        checkHandleMethodChecker(registry);
    }

    private void checkHandleMethodChecker(HandlerAnnotationRegistry registry) {
        HandleMethodChecker handleMethodChecker = getHandleMethodChecker(registry);
        handleMethodCheckerMediator.addMethodChecker(handleMethodChecker);
    }

    private HandleMethodChecker getHandleMethodChecker(HandlerAnnotationRegistry registry) {
        return findBeansOfTypeService.findBean(registry.getHandleMethodChecker())
                .orElseThrow(() -> new RuntimeException("HandleMethodChecker instance not found!"));
    }
}
