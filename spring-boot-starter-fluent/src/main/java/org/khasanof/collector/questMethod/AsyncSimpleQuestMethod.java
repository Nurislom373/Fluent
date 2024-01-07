package org.khasanof.collector.questMethod;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.khasanof.collector.GenericMethodContext;
import org.khasanof.context.FluentContextHolder;
import org.khasanof.enums.HandleClasses;
import org.khasanof.enums.HandleType;
import org.khasanof.executors.matcher.CompositeMatcher;
import org.khasanof.factories.invoker.method.InvokerMethodFactory;
import org.khasanof.models.invoker.SimpleInvoker;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author Nurislom
 * @see org.khasanof.collector.questMethod
 * @since 23.06.2023 23:46
 */
@Slf4j
public class AsyncSimpleQuestMethod implements BaseSearchMethod {

    private final InvokerMethodFactory invokerMethodFactory;
    private final GenericMethodContext<HandleClasses, Map<Method, Object>> methodContext;
    private final CompositeMatcher matcher;

    public AsyncSimpleQuestMethod(InvokerMethodFactory invokerMethodFactory, GenericMethodContext<HandleClasses, Map<Method, Object>> methodContext,
                                  CompositeMatcher matcher) {
        this.invokerMethodFactory = invokerMethodFactory;
        this.methodContext = methodContext;
        this.matcher = matcher;
    }

    @Override
    public Optional<SimpleInvoker> getMethodValueAnn(Object value, HandleClasses type) {
        log.info("contextHolder.getCurrentUpdate() = " + FluentContextHolder.getCurrentUpdate());
        System.out.printf("Enter type - %s, value - %s \n", type, value);
        CompletableFuture<Map.Entry<Method, Object>> supplyAsync;
        if (type.isMultiVersion()) {
            supplyAsync = CompletableFuture.supplyAsync(() -> methodContext.contains(type) ?
                            methodContext.find(type)
                                    .flatMap(functionRefMap -> functionRefMap.entrySet()
                                            .parallelStream().filter(aClass -> matcher.chooseMatcher(aClass.getKey(),
                                                    value, type.getType()))
                                            .findFirst())
                                    .orElse(null) : null)
                    .thenComposeAsync(s -> CompletableFuture.supplyAsync(() -> {
                        if (Objects.isNull(s)) {
                            return methodContext.contains(type.getSubHandleClasses()) ?
                                    methodContext.find(type.getSubHandleClasses())
                                            .flatMap(functionRefMap -> functionRefMap.entrySet()
                                                    .parallelStream().filter(aClass -> matcher.chooseMatcher(aClass.getKey(),
                                                            value, type.getSubHandleClasses().getType()))
                                                    .findFirst())
                                            .orElse(null) : null;
                        }
                        return s;
                    }));
        } else {
            supplyAsync = CompletableFuture.supplyAsync(
                    () -> methodContext.contains(type) ?
                            methodContext.find(type).flatMap(functionRefMap -> functionRefMap.entrySet()
                                    .parallelStream().filter(aClass -> matcher.chooseMatcher(aClass.getKey(),
                                            value, type.getType()))
                                    .findFirst()).orElse(null) : null);
        }
        return Optional.ofNullable(supplyAsync.thenApply(invokerMethodFactory::create).join());
    }

    @Override
    @SneakyThrows
    public Optional<SimpleInvoker> getHandleAnyMethod(HandleType handleType) {
        log.info("contextHolder.getCurrentUpdate() = " + FluentContextHolder.getCurrentUpdate());
        return CompletableFuture.supplyAsync(() -> methodContext.find(HandleClasses.HANDLE_ANY)
                .flatMap(functionRefMap -> functionRefMap.entrySet().parallelStream().filter(
                                clazz -> matcher.chooseMatcher(clazz.getKey(), handleType))
                        .map(invokerMethodFactory::create)
                        .findFirst())).get();
    }

    @Override
    @SneakyThrows
    public Set<SimpleInvoker> getAllHandleAnyMethod(HandleType handleType) {
        log.info("contextHolder.getCurrentUpdate() = " + FluentContextHolder.getCurrentUpdate());
        return CompletableFuture.supplyAsync(() -> methodContext.find(HandleClasses.HANDLE_ANY)
                        .map(functionRefMap -> functionRefMap.entrySet().parallelStream().filter(
                                        clazz -> matcher.chooseMatcher(clazz.getKey(), handleType))
                                .map(invokerMethodFactory::create)
                                .collect(Collectors.toSet()))
                        .orElse(Collections.emptySet())).get();
    }

    @Override
    public boolean contains(HandleClasses handleClasses) {
        return methodContext.contains(handleClasses);
    }

}
