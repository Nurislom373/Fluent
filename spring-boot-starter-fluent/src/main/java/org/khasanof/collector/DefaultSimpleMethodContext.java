package org.khasanof.collector;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.khasanof.collector.loader.BeansLoader;
import org.khasanof.collector.methodChecker.MethodCheckerAdapter;
import org.khasanof.enums.HandleClasses;
import org.khasanof.event.methodContext.MethodCollectedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The {@link DefaultSimpleMethodContext} class collects the methods from the classes corresponding
 * to the {@link org.khasanof.enums.ClassLevelTypes} enum.
 * <p>
 * <br/>
 * Author: Nurislom
 * <br/>
 * Date: 18.06.2023
 * <br/>
 * Time: 11:28
 * <br/>
 * Package: org.khasanof.core.collector
 */
@Slf4j
@Getter
@Component(DefaultSimpleMethodContext.NAME)
public class DefaultSimpleMethodContext implements SimpleMethodContext {

    public static final String NAME = "simpleMethodContextClass";
    private final BeansLoader resourceLoader;
    private final MethodCheckerAdapter checkerAdapter;
    private final ApplicationEventPublisher eventPublisher;
    private final Map<HandleClasses, Map<Method, Object>> beanMap = new HashMap<>();

    public DefaultSimpleMethodContext(BeansLoader resourceLoader, MethodCheckerAdapter checkerAdapter, ApplicationEventPublisher eventPublisher) {
        this.resourceLoader = resourceLoader;
        this.checkerAdapter = checkerAdapter;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<Map<Method, Object>> find(HandleClasses classes) {
        return Optional.of(beanMap.getOrDefault(classes, Collections.emptyMap()));
    }

    @Override
    public boolean contains(HandleClasses key) {
        return beanMap.containsKey(key);
    }

    @Override
    public void assembleMethods() {
        log.info("simple method assemble start!");
        setMethodClassMap();
    }

    // TODO rewrite it!
    void setMethodClassMap() {
        resourceLoader.getBeans().values().forEach(bean -> {
            final Class<?> clazz = bean.getClass();
            List<Method> methods = new ArrayList<>();
            if (hasInterface(clazz)) {
                methods.addAll(getInterfaceMethods(clazz));
            }
            if (clazz.getDeclaredMethods().length >= 1) {
                methods.addAll(Arrays.asList(clazz.getDeclaredMethods()));
            }
            methods.forEach(method -> {
                if (checkerAdapter.valid(method)) {
                    HandleClasses key = getMethodAnnotation(method);
                    if (beanMap.containsKey(key)) {
                        beanMap.get(key).put(method, bean);
                    } else {
                        beanMap.put(key, new HashMap<>() {{
                            put(method, bean);
                        }});
                    }
                }
            });
        });
        pushEvent();
    }

    private boolean hasInterface(Class<?> clazz) {
        return clazz.getInterfaces().length >= 1;
    }

    private List<Method> getInterfaceMethods(Class<?> clazz) {
        List<Method> methods = new ArrayList<>();
        if (clazz.getInterfaces().length >= 1) {
            Arrays.stream(clazz.getInterfaces())
                    .forEach(in -> methods.addAll(Arrays.asList(in.getDeclaredMethods())));
        }
        return methods;
    }

    private void pushEvent() {
        Map<HandleClasses, Integer> map = beanMap.entrySet().stream()
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(),
                        entry.getValue().size())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        eventPublisher.publishEvent(new MethodCollectedEvent(this, map));
    }

    private HandleClasses getMethodAnnotation(Method method) {
        Annotation[] annotations = method.getAnnotations();
        if (annotations.length == 0) {
            return null;
        } else {
            return HandleClasses.getHandleWithType(
                    HandleClasses.getAllAnnotations()
                            .stream().map(method::getAnnotation)
                            .filter(Objects::nonNull).map(Annotation::annotationType)
                            .findFirst().orElse(null));
        }
    }

}
