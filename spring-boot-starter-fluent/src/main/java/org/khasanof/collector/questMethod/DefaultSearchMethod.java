package org.khasanof.collector.questMethod;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.khasanof.collector.GenericMethodContext;
import org.khasanof.context.FluentContextHolder;
import org.khasanof.converter.HandleTypeConverter;
import org.khasanof.enums.HandleClasses;
import org.khasanof.enums.HandleType;
import org.khasanof.executors.matcher.CompositeMatcher;
import org.khasanof.factories.invoker.method.InvokerMethodFactory;
import org.khasanof.models.invoker.SimpleInvoker;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Nurislom
 * @see org.khasanof.collector.questMethod
 * @since 12/9/2023 8:11 PM
 */
@Slf4j
public class DefaultSearchMethod implements BaseSearchMethod {

    private final CompositeMatcher matcher;
    private final HandleTypeConverter handleTypeConverter;
    private final InvokerMethodFactory invokerMethodFactory;
    private final GenericMethodContext<HandleClasses, Map<Method, Object>> methodContext;

    public DefaultSearchMethod(HandleTypeConverter handleTypeConverter,
                               InvokerMethodFactory invokerMethodFactory,
                               GenericMethodContext<HandleClasses, Map<Method, Object>> methodContext,
                               CompositeMatcher matcher) {
        this.handleTypeConverter = handleTypeConverter;
        this.invokerMethodFactory = invokerMethodFactory;
        this.methodContext = methodContext;
        this.matcher = matcher;
    }

    @Override
    public boolean contains(HandleClasses handleClasses) {
        return methodContext.contains(handleClasses);
    }

    @Override
    public Optional<SimpleInvoker> getMethodValueAnn(Object value, HandleClasses type) {
        log.info("contextHolder.getCurrentUpdate() = " + FluentContextHolder.getCurrentUpdate());
        System.out.printf("Enter type - %s, value - %s \n", type, value);
        return Optional.ofNullable(findInvoker(value, type));
    }

    @Override
    public Optional<SimpleInvoker> getHandleAnyMethod(HandleType handleType) {
        return getInvokerWithHandleType(handleType);
    }

    private Optional<SimpleInvoker> getInvokerWithHandleType(HandleType handleType) {
        return methodContext.find(handleTypeConverter.fromConvert(handleType))
                .flatMap(functionRefMap -> functionRefMap.entrySet()
                        .stream()
                        .filter(clazz -> matcher.chooseMatcher(clazz.getKey(), handleType))
                        .findFirst().map(invokerMethodFactory::create));
    }

    @Override
    public Set<SimpleInvoker> getAllHandleAnyMethod(HandleType handleType) {
        return methodContext.find(HandleClasses.HANDLE_ANY)
                .map(functionRefMap -> functionRefMap.entrySet().stream().filter(
                                clazz -> matcher.chooseMatcher(clazz.getKey(), handleType))
                        .map(invokerMethodFactory::create)
                        .collect(Collectors.toSet())
                ).orElse(Collections.emptySet());
    }

    @Nullable
    private SimpleInvoker findInvoker(Object value, HandleClasses type) {
        if (type.isMultiVersion()) {
            return getHasSubTypeInvoker(value, type);
        } else {
            return getInvokerWithHandleType(value, type);
        }
    }

    @Nullable
    private SimpleInvoker getHasSubTypeInvoker(Object value, HandleClasses type) {
        return contains(type) ? tryGetSimpleInvoker(value, type) : null;
    }

    @Nullable
    private SimpleInvoker tryGetSimpleInvoker(Object value, HandleClasses type) {
        SimpleInvoker invoker = getInvokerWithHandleType(value, type);
        if (Objects.isNull(invoker)) {
            invoker = getSubInvoker(value, type);
        }
        return invoker;
    }

    @Nullable
    private SimpleInvoker getInvokerWithHandleType(Object value, HandleClasses type) {
        return methodContext.find(type).flatMap(functionRefMap -> functionRefMap.entrySet()
                .stream().filter(aClass -> matcher.chooseMatcher(aClass.getKey(),
                        value, type.getType()))
                .findFirst().map(invokerMethodFactory::create)).orElse(null);
    }

    @Nullable
    private SimpleInvoker getSubInvoker(Object value, HandleClasses type) {
        return methodContext.find(type.getSubHandleClasses()).flatMap(functionRefMap -> functionRefMap.entrySet()
                .stream().filter(aClass -> matcher.chooseMatcher(aClass.getKey(),
                        value, type.getSubHandleClasses().getType()))
                .findFirst().map(invokerMethodFactory::create)).orElse(null);
    }

}
