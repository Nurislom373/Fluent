package org.khasanof.config;

import org.khasanof.enums.ProcessType;
import org.khasanof.event.assembleMethods.AssembleMethodsEvent;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author Nurislom
 * @see org.khasanof.config
 * @since 05.07.2023 0:29
 */
@Component(value = FluentConfigRunner.NAME)
public class FluentConfigRunner implements InitializingBean {

    public static final String NAME = "commonFluentConfigRunner";

    private final ApplicationContext applicationContext;
    private final ApplicationProperties properties;

    public FluentConfigRunner(ApplicationContext applicationContext, ApplicationProperties properties) {
        this.applicationContext = applicationContext;
        this.properties = properties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, Config> beans = applicationContext.getBeansOfType(Config.class);
        ProcessType processType = properties.getBot().getProcessType();
        if (processType.equals(ProcessType.BOTH)) {
            beans.values().forEach(Config::runnable);
        } else {
            beans.values().forEach(config -> {
                if (config.processType().equals(ProcessType.BOTH)) {
                    CompletableFuture.runAsync(config::runnable);
                } else if (config.processType().equals(processType)) {
                    CompletableFuture.runAsync(config::runnable);
                }
            });
        }
        applicationContext.publishEvent(new AssembleMethodsEvent(this));
    }
}
