package org.khasanof.config;

import org.khasanof.enums.ProcessType;
import org.khasanof.event.assembleMethods.AssembleMethodsEvent;
import org.khasanof.exceptions.NotFoundException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

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
    private static final boolean statePresent;

    private final ApplicationContext applicationContext;
    private final ApplicationProperties properties;


    static {
        ClassLoader classLoader = FluentConfigRunner.class.getClassLoader();
        statePresent = ClassUtils.isPresent("org.khasanof.FluentStateAutoConfiguration", classLoader);
    }

    public FluentConfigRunner(ApplicationContext applicationContext, ApplicationProperties properties) {
        this.applicationContext = applicationContext;
        this.properties = properties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, Config> beans = applicationContext.getBeansOfType(Config.class);
        ProcessType processType = properties.getBot().getProcessType();
        checkDependency(processType);
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

    private void checkDependency(ProcessType processType) {
        if (processType.equals(ProcessType.BOTH) || processType.equals(ProcessType.STATE)) {
            if (!statePresent) {
                throw new NotFoundException("state dependency not found!");
            }
        }
    }

}
