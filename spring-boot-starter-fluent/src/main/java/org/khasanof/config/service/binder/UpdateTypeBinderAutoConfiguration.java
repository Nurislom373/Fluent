package org.khasanof.config.service.binder;

import org.khasanof.registry.binder.DefaultUpdateTypeBinderRegistry;
import org.khasanof.registry.binder.UpdateTypeBinderRegistry;
import org.khasanof.service.binder.DefaultUpdateTypeBinderService;
import org.khasanof.service.binder.UpdateTypeBinderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config.service.binder
 * @since 2/18/2024 5:31 PM
 */
@Configuration
public class UpdateTypeBinderAutoConfiguration {

    /**
     *
     * @return {@link UpdateTypeBinderRegistry} bean
     */
    @Bean
    public UpdateTypeBinderRegistry updateTypeBinderRegistry() {
        return new DefaultUpdateTypeBinderRegistry();
    }

    /**
     *
     * @param binderRegistry bean
     * @return {@link UpdateTypeBinderService} bean
     */
    @Bean
    public UpdateTypeBinderService updateTypeBinderService(UpdateTypeBinderRegistry binderRegistry) {
        return new DefaultUpdateTypeBinderService(binderRegistry);
    }
}
