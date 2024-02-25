package org.khasanof.config.collector;

import org.khasanof.collector.DefaultSimpleMethodContext;
import org.khasanof.collector.context.SimpleMethodContext;
import org.khasanof.collector.loader.HandlerLoader;
import org.khasanof.collector.method.checker.HandleMethodCheckerMediator;
import org.khasanof.factories.invoker.method.DefaultInvokerMethodFactory;
import org.khasanof.factories.invoker.method.InvokerMethodFactory;
import org.khasanof.mediator.MethodTypeDefinitionMediator;
import org.khasanof.service.annotation.handler.AnnotationHandlerService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config.collector
 * @since 2/8/2024 10:47 PM
 */
@Configuration
public class SimpleMethodContextConfiguration {

    @Bean(DefaultSimpleMethodContext.NAME)
    public SimpleMethodContext simpleMethodContext(HandlerLoader resourceLoader,
                                                   ApplicationEventPublisher eventPublisher,
                                                   InvokerMethodFactory invokerMethodFactory,
                                                   HandleMethodCheckerMediator checkerAdapter,
                                                   AnnotationHandlerService annotationHandlerService) {

        return new DefaultSimpleMethodContext(resourceLoader, checkerAdapter,
                eventPublisher, invokerMethodFactory, annotationHandlerService);
    }
}
