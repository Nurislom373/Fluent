package org.khasanof.config;

import org.khasanof.state.collector.StateConfigCollector;
import org.khasanof.state.repository.DefaultStateRepositoryStrategy;
import org.khasanof.state.repository.StateRepositoryStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config
 * @since 2/29/2024 9:34 PM
 */
@Configuration
public class StateRepositoryStrategyConfiguration {

    /**
     *
     * @param stateConfigCollector
     * @return {@link StateRepositoryStrategy} bean
     */
    @Bean
    public StateRepositoryStrategy stateRepositoryStrategy(StateConfigCollector stateConfigCollector) {
        return new DefaultStateRepositoryStrategy(stateConfigCollector);
    }
}
