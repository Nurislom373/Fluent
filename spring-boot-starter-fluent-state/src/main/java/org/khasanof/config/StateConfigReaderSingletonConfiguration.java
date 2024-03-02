package org.khasanof.config;

import org.khasanof.state.configurer.DefaultStateConfigurerReaderSingleton;
import org.khasanof.state.configurer.StateConfigurerReaderSingleton;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config
 * @since 3/2/2024 11:28 AM
 */
@Configuration
public class StateConfigReaderSingletonConfiguration {

    /**
     *
     * @return
     */
    @Bean
    public StateConfigurerReaderSingleton configurerReaderSingleton() {
        return new DefaultStateConfigurerReaderSingleton();
    }
}
