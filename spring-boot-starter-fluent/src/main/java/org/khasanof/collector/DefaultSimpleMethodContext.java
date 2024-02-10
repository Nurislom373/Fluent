package org.khasanof.collector;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.collector.context.SimpleMethodContext;
import org.khasanof.collector.loader.HandlerLoader;
import org.khasanof.collector.method.checker.HandleMethodCheckerMediator;
import org.khasanof.constants.FluentConstants;
import org.khasanof.enums.HandleAnnotation;
import org.khasanof.event.methodContext.MethodCollectedEvent;
import org.khasanof.factories.invoker.method.InvokerMethodFactory;
import org.khasanof.models.invoker.InvokerParam;
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
public class DefaultSimpleMethodContext implements SimpleMethodContext {

    public static final String NAME = "defaultSimpleMethodContext";
    private static final Set<String> IGNORE_METHOD_NAMES = FluentConstants.IGNORE_METHOD_NAMES;

    private final HandlerLoader resourceLoader;
    private final ApplicationEventPublisher eventPublisher;
    private final InvokerMethodFactory invokerMethodFactory;
    private final HandleMethodCheckerMediator checkerMediator;
    private final Map<HandleAnnotation, List<SimpleInvoker>> beanMap = new HashMap<>();

    public DefaultSimpleMethodContext(HandlerLoader resourceLoader,
                                      HandleMethodCheckerMediator checkerAdapter,
                                      ApplicationEventPublisher eventPublisher,
                                      InvokerMethodFactory invokerMethodFactory) {

        this.resourceLoader = resourceLoader;
        this.checkerMediator = checkerAdapter;
        this.eventPublisher = eventPublisher;
        this.invokerMethodFactory = invokerMethodFactory;
    }

    /**
     *
     * @return
     */
    @Override
    public Map<HandleAnnotation, List<SimpleInvoker>> findAll() {
        return beanMap;
    }

    /**
     *
     * @param classes
     * @return
     */
    @Override
    public Optional<List<SimpleInvoker>> find(HandleAnnotation classes) {
        return Optional.of(beanMap.getOrDefault(classes, Collections.emptyList()));
    }

    /**
     *
     * @param key
     * @return
     */
    @Override
    public boolean contains(HandleAnnotation key) {
        return beanMap.containsKey(key);
    }

    /**
     *
     */
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
        return resourceLoader.getHandlers().values();
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
        if (checkerMediator.check(method)) {
            HandleAnnotation key = getMethodAnnotation(method);

            if (beanMap.containsKey(key)) {
                beanMap.get(key)
                        .add(invokerMethodFactory.create(Map.entry(method, bean), createParams(method, key)));
                return;
            }
            putNewHandleAnnotation(bean, method, key);
        }
    }

    private void putNewHandleAnnotation(Object bean, Method method, HandleAnnotation key) {
        beanMap.put(key, new ArrayList<>() {{
            add(invokerMethodFactory.create(Map.entry(method, bean), createParams(method, key)));
        }});
    }

    private Map<InvokerParam, Object> createParams(Method method, HandleAnnotation handleAnnotation) {
        return new HashMap<>() {{
            put(InvokerParam.ANNOTATION, method.getAnnotation(handleAnnotation.getType()));
        }};
    }

    private void pushEvent() {
        Map<HandleAnnotation, Integer> map = beanMap.entrySet()
                .stream()
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(),
                        entry.getValue().size()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        eventPublisher.publishEvent(new MethodCollectedEvent(this, map));
    }
}
