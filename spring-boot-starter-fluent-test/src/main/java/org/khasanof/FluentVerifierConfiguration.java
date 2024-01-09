package org.khasanof;

import org.khasanof.factories.proxy.ProxyFluentBotFactory;
import org.khasanof.factories.executor.UpdateExecutorFactory;
import org.khasanof.factories.executor.SimulateExecutorServiceFactory;
import org.khasanof.verifier.DefaultFluentVerifier;
import org.khasanof.verifier.FluentVerifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/9/2024 9:02 PM
 */
@Configuration
@ConditionalOnClass({ProxyFluentBotFactoryConfiguration.class, UpdateExecutorFactoryConfiguration.class, SimulateExecutorServiceFactoryConfiguration.class})
public class FluentVerifierConfiguration {

    @Bean
    public FluentVerifier fluentVerifier(ProxyFluentBotFactory proxyFluentBotFactory, UpdateExecutorFactory updateExecutorFactory,
                                         SimulateExecutorServiceFactory serviceFactory) {
        return new DefaultFluentVerifier(proxyFluentBotFactory, updateExecutorFactory, serviceFactory);
    }

}
