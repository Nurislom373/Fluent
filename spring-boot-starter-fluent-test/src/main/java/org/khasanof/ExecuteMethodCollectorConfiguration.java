package org.khasanof;

import org.khasanof.execute.DefaultExecuteMethodCollector;
import org.khasanof.execute.DefaultExecuteMethodDataAdapter;
import org.khasanof.execute.ExecuteMethodCollector;
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
        return new ExecuteMethodCollectorConfig(new DefaultExecuteMethodDataAdapter(methodCollector));
    }

    public static class ExecuteMethodCollectorConfig implements InitializingBean {

        private final DefaultExecuteMethodDataAdapter defaultExecuteMethodDataAdapter;

        public ExecuteMethodCollectorConfig(DefaultExecuteMethodDataAdapter defaultExecuteMethodDataAdapter) {
            this.defaultExecuteMethodDataAdapter = defaultExecuteMethodDataAdapter;
        }

        @Override
        public void afterPropertiesSet() {
             defaultExecuteMethodDataAdapter.addDefaultMethods();
        }
    }
}
