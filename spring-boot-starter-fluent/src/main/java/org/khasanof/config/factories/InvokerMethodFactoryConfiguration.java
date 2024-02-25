package org.khasanof.config.factories;

import org.khasanof.factories.invoker.method.DefaultInvokerMethodFactory;
import org.khasanof.factories.invoker.method.InvokerMethodFactory;
import org.khasanof.feature.interceptor.InvokerMethodInterceptor;
import org.khasanof.service.FindBeansOfTypeService;
import org.khasanof.service.interceptor.invoke.DefaultInvokerMethodInterceptorService;
import org.khasanof.service.interceptor.invoke.InvokerMethodInterceptorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config.factories
 * @since 2/25/2024 8:14 PM
 */
@Configuration
public class InvokerMethodFactoryConfiguration {

    /**
     *
     * @param beansOfTypeService
     * @return {@link InvokerMethodInterceptorService} bean
     */
    @Bean
    public InvokerMethodInterceptorService invokerMethodInterceptorService(FindBeansOfTypeService beansOfTypeService) {
        DefaultInvokerMethodInterceptorService interceptorService = new DefaultInvokerMethodInterceptorService();
        interceptorService.addInterceptors(beansOfTypeService.findAllByList(InvokerMethodInterceptor.class));
        return interceptorService;
    }

    /**
     *
     * @param interceptorService
     * @return {@link InvokerMethodFactory} bean
     */
    @Bean
    public InvokerMethodFactory invokerMethodFactory(InvokerMethodInterceptorService interceptorService) {
        return new DefaultInvokerMethodFactory(interceptorService);
    }
}
