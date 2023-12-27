package org.khasanof.collector.loader;

import org.khasanof.annotation.ExceptionController;
import org.khasanof.annotation.StateController;
import org.khasanof.annotation.UpdateController;
import org.khasanof.config.FluentProperties;
import org.khasanof.config.Config;
import org.khasanof.enums.ClassLevelTypes;
import org.khasanof.enums.ProcessType;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author: Nurislom
 * <br/>
 * Date: 18.06.2023
 * <br/>
 * Time: 11:32
 * <br/>
 * Package: org.khasanof.core.collector
 */
@Component
public class DefaultHandlerLoader implements Config, HandlerLoader {

    private final FluentProperties.Bot bot;
    private final ApplicationContext applicationContext;
    private final Map<String, Object> beanMap = new ConcurrentHashMap<>();
    private final Set<Class<? extends Annotation>> classLevelAnnotations = new HashSet<>();

    public DefaultHandlerLoader(ApplicationContext applicationContext, FluentProperties properties) {
        this.applicationContext = applicationContext;
        this.bot = properties.getBot();
    }

    @Override
    public Map<String, Object> getBeans() {
        return this.beanMap;
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return applicationContext.getBeansOfType(type);
    }

    @Override
    public Map<String, Object> getBeans(Class<? extends Annotation> annotation) {
        return applicationContext.getBeansWithAnnotation(annotation);
    }

    @Override
    public void runnable() {
        ProcessType processType = bot.getProcessType();
        if (processType.equals(ProcessType.BOTH)) {
            this.classLevelAnnotations.addAll(ClassLevelTypes.getAllAnnotations());
        } else {
            if (processType.equals(ProcessType.STATE)) {
                this.classLevelAnnotations.add(StateController.class);
            }
            this.classLevelAnnotations.add(UpdateController.class);
            this.classLevelAnnotations.add(ExceptionController.class);
        }
        fillBeanMap();
    }

    private void fillBeanMap() {
        for (var classLevelAnnotation : classLevelAnnotations) {
            var validBeansTake = applicationContext.getBeansWithAnnotation(classLevelAnnotation);
            if (!validBeansTake.isEmpty()) {
                this.beanMap.putAll(validBeansTake);
            }
        }
    }

    @Override
    public ProcessType processType() {
        return ProcessType.BOTH;
    }
}
