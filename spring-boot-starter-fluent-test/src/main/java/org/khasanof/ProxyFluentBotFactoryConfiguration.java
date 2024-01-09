package org.khasanof;

import org.khasanof.context.singleton.GenericSingleton;
import org.khasanof.execute.ExecuteMethodCollector;
import org.khasanof.factories.proxy.DefaultProxyFluentBotFactory;
import org.khasanof.factories.proxy.ProxyFluentBotFactory;
import org.khasanof.handler.DefaultProxyMethodHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/9/2024 9:04 PM
 */
@Configuration
@ConditionalOnClass(ExecuteMethodCollectorConfiguration.class)
public class ProxyFluentBotFactoryConfiguration {

    @Bean
    public ProxyFluentBotFactory proxyFluentBotFactory(GenericSingleton<FluentBot> genericSingleton,
                                                       ExecuteMethodCollector methodCollector) {
        return new DefaultProxyFluentBotFactory(genericSingleton, new DefaultProxyMethodHandler(methodCollector));
    }

}
