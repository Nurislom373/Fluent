package org.khasanof.config.registry;

import org.khasanof.registry.state.DefaultUserProceedStateRegistryContainer;
import org.khasanof.registry.state.UserProceedStateRegistryContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config.registry
 * @since 6/8/2024 2:50 AM
 */
@Configuration
public class UserProceedStateRegistryContainerConfiguration {

    /**
     *
     * @return
     */
    @Bean
    public UserProceedStateRegistryContainer proceedStateRegistryContainer() {
        return new DefaultUserProceedStateRegistryContainer();
    }
}
