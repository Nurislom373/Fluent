package org.khasanof.collector.questMethod;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.khasanof.collector.GenericMethodContext;
import org.khasanof.context.FluentContextHolder;
import org.khasanof.enums.HandleClasses;
import org.khasanof.enums.HandleType;
import org.khasanof.executors.matcher.CompositeMatcher;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Nurislom
 * @see org.khasanof.collector.questMethod
 * @since 12/9/2023 8:11 PM
 */
@Slf4j
@Component
public class DefaultSearchMethod implements BaseSearchMethod {

    private final GenericMethodContext<HandleClasses, Map<Method, Object>> methodContext;
    private final CompositeMatcher matcher;

    public DefaultSearchMethod(GenericMethodContext<HandleClasses, Map<Method, Object>> methodContext, CompositeMatcher matcher) {
        this.methodContext = methodContext;
        this.matcher = matcher;
    }

    @Override
    public boolean contains(HandleClasses handleClasses) {
        return methodContext.contains(handleClasses);
    }

    @Override
    public SimpleInvoker getMethodValueAnn(Object value, HandleClasses type) {
        log.info("contextHolder.getCurrentUpdate() = " + FluentContextHolder.getCurrentUpdate());
        System.out.printf("Enter type - %s, value - %s \n", type, value);
        return findInvoker(value, type);
    }

    @Override
    public SimpleInvoker getHandleAnyMethod(HandleType handleType) {
        return getInvokerWithHandleType(handleType);
    }

    @Nullable
    private SimpleInvoker getInvokerWithHandleType(HandleType handleType) {
        return methodContext.find(handleType.getHandleClasses()).entrySet().stream()
                .filter(clazz -> matcher.chooseMatcher(clazz.getKey(), handleType))
                .findFirst().map(SearchMethodUtils::createResultInvoker).orElse(null);
    }

    @Override
    public Set<SimpleInvoker> getAllHandleAnyMethod(HandleType handleType) {
        return methodContext.find(HandleClasses.HANDLE_ANY).entrySet().stream().filter(
                        clazz -> matcher.chooseMatcher(clazz.getKey(), handleType))
                .map(SearchMethodUtils::createResultInvoker)
                .collect(Collectors.toSet());
    }

    @Nullable
    private SimpleInvoker findInvoker(Object value, HandleClasses type) {
        if (type.isHasSubType()) {
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
        return methodContext.find(type).entrySet()
                .stream().filter(aClass -> matcher.chooseMatcher(aClass.getKey(),
                        value, type.getType()))
                .findFirst().map(SearchMethodUtils::createResultInvoker)
                .orElse(null);
    }

    @Nullable
    private SimpleInvoker getSubInvoker(Object value, HandleClasses type) {
        return methodContext.find(type.getSubHandleClasses()).entrySet()
                .stream().filter(aClass -> matcher.chooseMatcher(aClass.getKey(),
                        value, type.getSubHandleClasses().getType()))
                .findFirst().map(SearchMethodUtils::createResultInvoker)
                .orElse(null);
    }

}
