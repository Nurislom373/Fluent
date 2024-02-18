package org.khasanof.service;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.khasanof.collector.context.SimpleMethodContext;
import org.khasanof.context.FluentContextHolder;
import org.khasanof.executors.matcher.MatcherMediator;
import org.khasanof.feature.AnnotationHandler;
import org.khasanof.models.collector.FindHandlerMethod;
import org.khasanof.models.invoker.SimpleInvoker;

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

    private final MatcherMediator matcher;
    private final SimpleMethodContext methodContext;

    public DefaultSearchHandlerMethodService(MatcherMediator matcher, SimpleMethodContext methodContext) {
        this.matcher = matcher;
        this.methodContext = methodContext;
    }

    @Override
    public Optional<SimpleInvoker> find(FindHandlerMethod findHandlerMethod) {
        System.out.printf("Enter type - %s, value - %s \n", findHandlerMethod.getKey(), findHandlerMethod.getValue());
        return Optional.ofNullable(findInvoker(findHandlerMethod));
    }

    @Nullable
    private SimpleInvoker findInvoker(FindHandlerMethod handlerMethod) {
        if (Objects.equals(handlerMethod.getKey().isRepeatable(), Boolean.TRUE)) {
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
                .flatMap(functionRefMap -> functionRefMap.stream()
                        .filter(simpleInvoker -> matcher.match(simpleInvoker.getMethod(),
                                handlerMethod.getValue(), getAnnotationClass(handlerMethod.getKey())))
                        .findFirst())
                .orElse(null);
    }

    @Nullable
    private SimpleInvoker getSubInvoker(FindHandlerMethod handlerMethod) {
        return methodContext.find(getSubHandleClasses(handlerMethod))
                .flatMap(functionRefMap -> functionRefMap.stream()
                        .filter(simpleInvoker -> matcher.match(simpleInvoker.getMethod(),
                                handlerMethod.getValue(), getAnnotationClass(getSubHandleClasses(handlerMethod))))
                        .findFirst())
                .orElse(null);
    }

    private AnnotationHandler getSubHandleClasses(FindHandlerMethod handlerMethod) {
        return handlerMethod.getKey().repeatableAnnotationHandler();
    }

    private Class<? extends Annotation> getAnnotationClass(AnnotationHandler annotationHandler) {
        return annotationHandler.getAnnotation();
    }
}
