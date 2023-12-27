package org.khasanof.config.method;

import org.khasanof.collector.method.checker.DefaultHandleMethodCheckerAdapter;
import org.khasanof.collector.method.checker.HandleMethodChecker;
import org.khasanof.collector.method.checker.HandleMethodCheckerAdapter;
import org.khasanof.collector.method.checker.ProcessTypeHandleMethodChecker;
import org.khasanof.config.FluentProperties;
import org.khasanof.custom.ProcessTypeResolver;
import org.khasanof.enums.ProcessType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.config.method
 * @since 12/26/2023 10:29 PM
 */
@Configuration(proxyBeanMethods = false)
public class HandleMethodCheckerAutoConfiguration {

    @Bean
    @DependsOn(HandleMethodCheckerConfig.NAME)
    public HandleMethodCheckerAdapter handleMethodCheckerAdapter(HandleMethodCheckerConfig config) {
        var methodCheckerAdapter = new DefaultHandleMethodCheckerAdapter();
        methodCheckerAdapter.setMethodCheckers(config.getHandleMethodChecker());
        return methodCheckerAdapter;
    }

    @Bean(HandleMethodCheckerConfig.NAME)
    public HandleMethodCheckerConfig handleMethodCheckerConfig(ApplicationContext applicationContext, FluentProperties fluentProperties) {
        var bot = fluentProperties.getBot();
        return new HandleMethodCheckerConfig(bot.getProcessType(), applicationContext);
    }

    public static class HandleMethodCheckerConfig {

        public static final String NAME = "handleMethodCheckerConfig";
        private final ProcessType processType;
        private final ApplicationContext applicationContext;
        private final List<HandleMethodChecker> methodCheckers = new ArrayList<>();

        public HandleMethodCheckerConfig(ProcessType processType,
                                         ApplicationContext applicationContext) {

            this.processType = processType;
            this.applicationContext = applicationContext;
        }

        public List<HandleMethodChecker> getHandleMethodChecker() {
            if (methodCheckers.isEmpty()) {
                runnable();
            }
            return this.methodCheckers;
        }

        public void runnable() {
            applicationContext.getBeansOfType(ProcessTypeHandleMethodChecker.class)
                    .values().stream()
                    .filter(this::isAcceptProcessType)
                    .forEach(methodCheckers::add);
        }

        private boolean isAcceptProcessType(ProcessTypeHandleMethodChecker processTypeHandleMethodChecker) {
            return ProcessTypeResolver.hasAcceptProcessType(processTypeHandleMethodChecker, processType);
        }
    }
}
