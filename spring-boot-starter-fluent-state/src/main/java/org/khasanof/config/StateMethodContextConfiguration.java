package org.khasanof.config;

import org.khasanof.collector.DefaultStateMethodContext;
import org.khasanof.collector.StateMethodContext;
import org.khasanof.collector.loader.HandlerLoader;
import org.khasanof.factories.invoker.StateInvokerMethodFactory;
import org.khasanof.service.interceptor.invoke.InvokerMethodInterceptorService;
import org.khasanof.state.validator.StateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config
 * @since 2/8/2024 10:51 PM
 */
@Configuration
public class StateMethodContextConfiguration {

    /**
     *
     * @param resourceLoader
     * @param stateValidator
     * @return {@link StateMethodContext} bean
     */
    @Bean(DefaultStateMethodContext.NAME)
    public StateMethodContext stateMethodContext(HandlerLoader resourceLoader,
                                                 StateValidator stateValidator,
                                                 InvokerMethodInterceptorService interceptorService) {

        return new DefaultStateMethodContext(resourceLoader, stateValidator, new StateInvokerMethodFactory(interceptorService));
    }
}
