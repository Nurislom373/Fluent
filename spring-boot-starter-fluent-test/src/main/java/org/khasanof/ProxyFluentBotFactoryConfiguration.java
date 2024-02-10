package org.khasanof;

import org.khasanof.adapter.ExecMethodResponseAdapter;
import org.khasanof.execute.ExecuteMethodCollector;
import org.khasanof.factories.proxy.DefaultProxyFluentTemplateFactory;
import org.khasanof.factories.proxy.ProxyFluentTemplateFactory;
import org.khasanof.handler.DefaultExecuteMethodChecker;
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

    /**
     *
     * @param methodCollector
     * @param methodResponseAdapter
     * @return
     */
    @Bean
    public ProxyFluentTemplateFactory proxyFluentBotFactory(ExecuteMethodCollector methodCollector,
                                                            ExecMethodResponseAdapter methodResponseAdapter) {
        return new DefaultProxyFluentTemplateFactory(new DefaultExecuteMethodChecker(methodCollector), methodResponseAdapter);
    }
}
