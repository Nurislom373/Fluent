package org.khasanof.config;

import org.khasanof.collector.DefaultStateMethodContext;
import org.khasanof.collector.StateMethodContext;
import org.khasanof.collector.loader.HandlerLoader;
import org.khasanof.factories.invoker.StateInvokerMethodFactory;
import org.khasanof.state.collector.StateValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config
 * @since 2/8/2024 10:51 PM
 */
@Configuration
public class StateMethodContextConfiguration {

    @Bean(DefaultStateMethodContext.NAME)
//    @ConditionalOnBean({HandlerLoader.class, StateValidator.class})
    public StateMethodContext stateMethodContext(HandlerLoader resourceLoader, StateValidator stateValidator) {
        return new DefaultStateMethodContext(resourceLoader, stateValidator, new StateInvokerMethodFactory());
    }
}
