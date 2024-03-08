package org.khasanof.config.factories.condition;

import org.khasanof.factories.condition.DefaultFluentConditionFactory;
import org.khasanof.factories.condition.FluentConditionFactory;
import org.khasanof.registry.condition.DefaultFluentConditionRegistry;
import org.khasanof.registry.condition.FluentConditionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config.factories.condition
 * @since 2/28/2024 10:33 PM
 */
@Configuration
public class FluentConditionConfiguration {

    /**
     *
     * @return {@link FluentConditionRegistry} bean
     */
    @Bean
    public FluentConditionRegistry fluentConditionRegistry() {
        return new DefaultFluentConditionRegistry();
    }

    /**
     *
     * @param fluentConditionRegistry
     * @return {@link FluentConditionFactory} bean
     */
    @Bean
    public FluentConditionFactory fluentConditionFactory(FluentConditionRegistry fluentConditionRegistry) {
        return new DefaultFluentConditionFactory(fluentConditionRegistry);
    }
}
