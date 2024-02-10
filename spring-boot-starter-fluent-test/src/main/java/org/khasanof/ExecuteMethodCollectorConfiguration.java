package org.khasanof;

import org.khasanof.execute.DefaultExecuteMethodCollector;
import org.khasanof.execute.DefaultExecuteMethodDataRegistry;
import org.khasanof.execute.ExecuteMethodCollector;
import org.khasanof.method.DefaultExecuteMethodReflect;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/9/2024 9:05 PM
 */
@Configuration
public class ExecuteMethodCollectorConfiguration {

    @Bean
    public ExecuteMethodCollector executeMethodCollector() {
        return new DefaultExecuteMethodCollector();
    }

    @Bean
    public ExecuteMethodCollectorConfig executeMethodCollectorDataConfig(ExecuteMethodCollector methodCollector) {
        return new ExecuteMethodCollectorConfig(new DefaultExecuteMethodDataRegistry(methodCollector, new DefaultExecuteMethodReflect()));
    }

    public static class ExecuteMethodCollectorConfig implements InitializingBean {

        private final DefaultExecuteMethodDataRegistry defaultExecuteMethodDataRegistry;

        public ExecuteMethodCollectorConfig(DefaultExecuteMethodDataRegistry defaultExecuteMethodDataRegistry) {
            this.defaultExecuteMethodDataRegistry = defaultExecuteMethodDataRegistry;
        }

        @Override
        public void afterPropertiesSet() {
             defaultExecuteMethodDataRegistry.addDefaultMethods();
        }
    }
}
