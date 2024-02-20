package org.khasanof.collector;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.collector.context.SimpleMethodContext;
import org.khasanof.collector.loader.HandlerLoader;
import org.khasanof.collector.method.checker.HandleMethodCheckerMediator;
import org.khasanof.constants.FluentConstants;
import org.khasanof.event.methodContext.MethodCollectedEvent;
import org.khasanof.factories.invoker.method.InvokerMethodFactory;
import org.khasanof.feature.annotation.AnnotationHandler;
import org.khasanof.models.invoker.InvokerParam;
import org.khasanof.models.invoker.SimpleInvoker;
import org.khasanof.service.annotation.handler.AnnotationHandlerService;
import org.springframework.context.ApplicationEventPublisher;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
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
public class DefaultSimpleMethodContext implements SimpleMethodContext {

    public static final String NAME = "defaultSimpleMethodContext";
    private static final Set<String> IGNORE_METHOD_NAMES = FluentConstants.IGNORE_METHOD_NAMES;

    private final HandlerLoader resourceLoader;
    private final ApplicationEventPublisher eventPublisher;
    private final InvokerMethodFactory invokerMethodFactory;
    private final HandleMethodCheckerMediator checkerMediator;
    private final AnnotationHandlerService annotationHandlerService;
    private final Map<AnnotationHandler, List<SimpleInvoker>> beanMap = new HashMap<>();

    public DefaultSimpleMethodContext(HandlerLoader resourceLoader,
                                      HandleMethodCheckerMediator checkerAdapter,
                                      ApplicationEventPublisher eventPublisher,
                                      InvokerMethodFactory invokerMethodFactory,
                                      AnnotationHandlerService annotationHandlerService) {

        this.resourceLoader = resourceLoader;
        this.checkerMediator = checkerAdapter;
        this.eventPublisher = eventPublisher;
        this.invokerMethodFactory = invokerMethodFactory;
        this.annotationHandlerService = annotationHandlerService;
    }

    /**
     *
     * @return
     */
    @Override
    public Map<AnnotationHandler, List<SimpleInvoker>> findAll() {
        return beanMap;
    }

    /**
     *
     * @param classes
     * @return
     */
    @Override
    public Optional<List<SimpleInvoker>> find(AnnotationHandler classes) {
        return Optional.of(beanMap.getOrDefault(classes, Collections.emptyList()));
    }

    /**
     *
     * @param key
     * @return
     */
    @Override
    public boolean contains(AnnotationHandler key) {
        return beanMap.containsKey(key);
    }

    /**
     *
     */
    @Override
    public void assemble() {
        log.info("Default Method Context Start");
        assembleMethodsInternal();
    }

    private void assembleMethodsInternal() {
        Collection<Object> values = getValues();
        values.forEach(this::processHandlerBean);
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
            acceptEachMethodInternal(bean, method);
        }
    }

    private void acceptEachMethodInternal(Object bean, Method method) {
        annotationHandlerService.findByMethod(method)
                .ifPresent(handler -> existAddOrPutNewEntry(bean, method, handler));
    }

    private void existAddOrPutNewEntry(Object bean, Method method, AnnotationHandler handler) {
        if (beanMap.containsKey(handler)) {
            beanMap.get(handler).add(invokerMethodFactory.create(Map.entry(method, bean), createParams(method, handler)));
            return;
        }
        putNewAnnotationHandler(bean, method, handler);
    }

    private void putNewAnnotationHandler(Object bean, Method method, AnnotationHandler key) {
        beanMap.put(key, new ArrayList<>() {{
            add(invokerMethodFactory.create(Map.entry(method, bean), createParams(method, key)));
        }});
    }

    private Map<InvokerParam, Object> createParams(Method method, AnnotationHandler annotationHandler) {
        return new HashMap<>() {{
            put(InvokerParam.ANNOTATION, method.getAnnotation(annotationHandler.getAnnotation()));
            put(InvokerParam.ANNOTATION_TYPE, annotationHandler.getAnnotation().componentType());
        }};
    }

    private void pushEvent() {
        Map<AnnotationHandler, Integer> map = beanMap.entrySet()
                .stream()
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(),
                        entry.getValue().size()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        eventPublisher.publishEvent(new MethodCollectedEvent(this, map));
    }
}
