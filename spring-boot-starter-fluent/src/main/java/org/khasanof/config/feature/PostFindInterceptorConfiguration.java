package org.khasanof.config.feature;

import org.khasanof.service.interceptor.invoker.DefaultPostFindInvokerInterceptorService;
import org.khasanof.service.interceptor.invoker.PostFindInvokerInterceptorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config.feature
 * @since 2/28/2024 11:25 PM
 */
@Configuration
public class PostFindInterceptorConfiguration {

    /**
     *
     * @return {@link PostFindInvokerInterceptorService} bean
     */
    @Bean
    public PostFindInvokerInterceptorService invokerInterceptorService() {
        return new DefaultPostFindInvokerInterceptorService();
    }
}
