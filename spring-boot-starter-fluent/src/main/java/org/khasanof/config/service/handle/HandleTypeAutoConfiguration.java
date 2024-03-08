package org.khasanof.config.service.handle;

import org.khasanof.registry.handle.DefaultHandleTypeFindRegistry;
import org.khasanof.registry.handle.HandleTypeFindRegistry;
import org.khasanof.service.annotation.type.DefaultHandleTypeService;
import org.khasanof.service.annotation.type.HandleTypeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config.service.handle
 * @since 2/18/2024 5:29 PM
 */
@Configuration
public class HandleTypeAutoConfiguration {

    /**
     *
     * @return {@link HandleTypeFindRegistry} bean
     */
    @Bean
    public HandleTypeFindRegistry handleTypeFindRegistry() {
        return new DefaultHandleTypeFindRegistry();
    }

    /**
     *
     * @return {@link HandleTypeService} bean
     */
    @Bean
    public HandleTypeService handleTypeService(HandleTypeFindRegistry handleTypeFindRegistry) {
        return new DefaultHandleTypeService(handleTypeFindRegistry);
    }
}
