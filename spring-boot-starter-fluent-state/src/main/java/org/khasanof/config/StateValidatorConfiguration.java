package org.khasanof.config;

import org.khasanof.state.collector.StateConfigCollector;
import org.khasanof.state.validator.DefaultStateValidator;
import org.khasanof.state.validator.StateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config
 * @since 2/29/2024 9:31 PM
 */
@Configuration
public class StateValidatorConfiguration {

    /**
     *
     * @param stateConfigCollector
     * @return {@link StateValidator} bean
     */
    @Bean
    public StateValidator stateValidator(StateConfigCollector stateConfigCollector) {
        return new DefaultStateValidator(stateConfigCollector);
    }
}
