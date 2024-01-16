package org.khasanof.service;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.khasanof.collector.context.SimpleMethodContext;
import org.khasanof.context.FluentContextHolder;
import org.khasanof.enums.HandleClasses;
import org.khasanof.executors.matcher.CommonMatcher;
import org.khasanof.factories.invoker.method.InvokerMethodFactory;
import org.khasanof.models.collector.FindHandlerMethod;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.service
 * @since 1/16/2024 1:02 AM
 */
@Slf4j
public class DefaultSearchHandlerMethodService implements SearchHandlerMethodService {

    private final CommonMatcher matcher;
    private final SimpleMethodContext methodContext;
    private final InvokerMethodFactory invokerMethodFactory;

    public DefaultSearchHandlerMethodService(CommonMatcher matcher,
                                             SimpleMethodContext methodContext,
                                             InvokerMethodFactory invokerMethodFactory) {

        this.matcher = matcher;
        this.methodContext = methodContext;
        this.invokerMethodFactory = invokerMethodFactory;
    }

    @Override
    public Optional<SimpleInvoker> find(FindHandlerMethod findHandlerMethod) {
        log.info("contextHolder.getCurrentUpdate() = " + FluentContextHolder.getCurrentUpdate());
        System.out.printf("Enter type - %s, value - %s \n", findHandlerMethod.getKey(), findHandlerMethod.getValue());

        return Optional.ofNullable(findInvoker(findHandlerMethod));
    }

    @Nullable
    private SimpleInvoker findInvoker(FindHandlerMethod handlerMethod) {
        if (Objects.equals(handlerMethod.getKey().isMultiVersion(), Boolean.TRUE)) {
            return getHasSubTypeInvoker(handlerMethod);
        }
        return getInvokerWithHandleType(handlerMethod);
    }

    @Nullable
    private SimpleInvoker getHasSubTypeInvoker(FindHandlerMethod handlerMethod) {
        return methodContext.contains(handlerMethod.getKey()) ? tryGetSimpleInvoker(handlerMethod) : null;
    }

    @Nullable
    private SimpleInvoker tryGetSimpleInvoker(FindHandlerMethod handlerMethod) {
        SimpleInvoker invoker = getInvokerWithHandleType(handlerMethod);

        if (Objects.isNull(invoker)) {
            invoker = getSubInvoker(handlerMethod);
        }
        return invoker;
    }

    @Nullable
    private SimpleInvoker getInvokerWithHandleType(FindHandlerMethod handlerMethod) {
        return methodContext.find(handlerMethod.getKey())
                .flatMap(functionRefMap -> functionRefMap.entrySet()
                        .stream()
                        .filter(aClass -> matcher.chooseMatcher(aClass.getKey(),
                                handlerMethod.getValue(), getType(handlerMethod.getKey())))
                        .findFirst()
                        .map(invokerMethodFactory::create))
                .orElse(null);
    }

    @Nullable
    private SimpleInvoker getSubInvoker(FindHandlerMethod handlerMethod) {
        return methodContext.find(getSubHandleClasses(handlerMethod))
                .flatMap(functionRefMap -> functionRefMap.entrySet()
                        .stream()
                        .filter(aClass -> matcher.chooseMatcher(aClass.getKey(),
                                handlerMethod.getValue(), getType(getSubHandleClasses(handlerMethod))))
                        .findFirst()
                        .map(invokerMethodFactory::create))
                .orElse(null);
    }

    private HandleClasses getSubHandleClasses(FindHandlerMethod handlerMethod) {
        return handlerMethod.getKey().getSubHandleClasses();
    }

    private Class<? extends Annotation> getType(HandleClasses handlerMethod) {
        return handlerMethod.getType();
    }

}
