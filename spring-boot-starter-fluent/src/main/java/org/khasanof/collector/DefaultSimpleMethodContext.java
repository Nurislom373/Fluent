package org.khasanof.collector;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.khasanof.collector.context.SimpleMethodContext;
import org.khasanof.collector.loader.HandlerLoader;
import org.khasanof.collector.method.checker.HandleMethodCheckerAdapter;
import org.khasanof.constants.FluentConstants;
import org.khasanof.enums.HandleAnnotations;
import org.khasanof.event.methodContext.MethodCollectedEvent;
import org.khasanof.factories.invoker.method.InvokerMethodFactory;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import static org.khasanof.utils.BaseUtils.getMethodAnnotation;

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

    public static final String NAME = "defaultSimpleMethodContext";
    private static final Set<String> IGNORE_METHOD_NAMES = FluentConstants.IGNORE_METHOD_NAMES;

    private final HandlerLoader resourceLoader;
    private final ApplicationEventPublisher eventPublisher;
    private final InvokerMethodFactory invokerMethodFactory;
    private final HandleMethodCheckerAdapter checkerAdapter;
    private final Map<HandleAnnotations, List<SimpleInvoker>> beanMap = new HashMap<>();

    public DefaultSimpleMethodContext(HandlerLoader resourceLoader,
                                      HandleMethodCheckerAdapter checkerAdapter,
                                      ApplicationEventPublisher eventPublisher,
                                      InvokerMethodFactory invokerMethodFactory) {

        this.resourceLoader = resourceLoader;
        this.checkerAdapter = checkerAdapter;
        this.eventPublisher = eventPublisher;
        this.invokerMethodFactory = invokerMethodFactory;
    }

    @Override
    public Optional<List<SimpleInvoker>> find(HandleAnnotations classes) {
        return Optional.of(beanMap.getOrDefault(classes, Collections.emptyList()));
    }

    @Override
    public boolean contains(HandleAnnotations key) {
        return beanMap.containsKey(key);
    }

    @Override
    public void assembleMethods() {
        log.debug("Default Method Context Start");
        assembleMethodsInternal();
    }

    private void assembleMethodsInternal() {
        getValues().forEach(this::processHandlerBean);
        pushEvent();
    }

    private Collection<Object> getValues() {
        return resourceLoader.getHandlers()
                .values();
    }

    private void processHandlerBean(Object bean) {
        final Class<?> clazz = bean.getClass();
        List<Method> methods = new CopyOnWriteArrayList<>();

        checkDeclaredMethods(clazz, methods);
        ignoreSomeMethods(methods);

        iterateMethods(bean, methods);
    }

    private void checkDeclaredMethods(Class<?> clazz, List<Method> methods) {
        if (hasInterface(clazz)) {
            methods.addAll(getInterfaceMethods(clazz));
        }
        if (clazz.getDeclaredMethods().length >= 1) {
            methods.addAll(Arrays.asList(clazz.getDeclaredMethods()));
        }
    }

    private void ignoreSomeMethods(List<Method> methods) {
        methods.removeIf(method -> IGNORE_METHOD_NAMES.contains(method.getName()) || method.getAnnotations().length == 0);
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

    private void iterateMethods(Object bean, List<Method> methods) {
        methods.forEach(method -> acceptEachMethod(bean, method));
    }

    private void acceptEachMethod(Object bean, Method method) {
        if (checkerAdapter.check(method)) {
            HandleAnnotations key = getMethodAnnotation(method);

            if (beanMap.containsKey(key)) {
                beanMap.get(key)
                        .add(invokerMethodFactory.create(Map.entry(method, bean)));
                return;
            }
            putNewHandleClasses(bean, method, key);
        }
    }

    private void putNewHandleClasses(Object bean, Method method, HandleAnnotations key) {
        beanMap.put(key, new ArrayList<>() {{
            add(invokerMethodFactory.create(Map.entry(method, bean)));
        }});
    }

    private void pushEvent() {
        Map<HandleAnnotations, Integer> map = beanMap.entrySet()
                .stream()
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(),
                        entry.getValue().size()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        eventPublisher.publishEvent(new MethodCollectedEvent(this, map));
    }

}
