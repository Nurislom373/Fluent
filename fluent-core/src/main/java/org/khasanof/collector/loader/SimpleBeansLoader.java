package org.khasanof.collector.loader;

import org.khasanof.annotation.ExceptionController;
import org.khasanof.annotation.StateController;
import org.khasanof.annotation.UpdateController;
import org.khasanof.config.ApplicationProperties;
import org.khasanof.config.Config;
import org.khasanof.enums.ClassLevelTypes;
import org.khasanof.enums.ProcessType;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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
public class SimpleBeansLoader implements Config, BeansLoader {

    private final ApplicationProperties.Bot bot;
    private final ApplicationContext applicationContext;
    private final Map<String, Object> beanMap = new ConcurrentHashMap<>();
    private final Set<Class<? extends Annotation>> classLevelAnnotations = new HashSet<>();

    public SimpleBeansLoader(ApplicationContext applicationContext, ApplicationProperties properties) {
        this.applicationContext = applicationContext;
        this.bot = properties.getBot();
    }

    @Override
    public Map<String, Object> getBeans() {
        return this.beanMap;
    }

    private void beanMapFiller(Map<String, Object> beanMap) {
        for (Class<? extends Annotation> classLevelAnnotation : classLevelAnnotations) {
            Map<String, Object> validBeansTake = applicationContext.getBeansWithAnnotation(classLevelAnnotation);
            if (!validBeansTake.isEmpty()) {
                beanMap.putAll(validBeansTake);
            }
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return applicationContext.getBeansOfType(type);
    }

    @Override
    public Map<String, Object> getBeans(Class<? extends Annotation> annotation) {
        return applicationContext.getBeansWithAnnotation(annotation);
    }

    private Map<String, Object> validBeansTake(Map<String, Object> beanMap) {
        return beanMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private boolean hasAnnotationMethodLevel(Method method, Set<Class<? extends Annotation>> annotations) {
        int length = method.getAnnotations().length;
        if (length == 0) {
            return false;
        } else if (length == 1) {
            return annotations.stream()
                    .anyMatch(method::isAnnotationPresent);
        } else {
            int count = 0;
            for (Annotation annotation : method.getAnnotations()) {
                if (annotations.contains(annotation.annotationType())) {
                    count++;
                }
            }
            if (count == 1) {
                return true;
            }
            throw new RuntimeException("handle annotations are required to be single!");
        }
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
        CompletableFuture.runAsync(() -> beanMapFiller(beanMap));
    }

    @Override
    public ProcessType processType() {
        return ProcessType.BOTH;
    }
}
