package org.khasanof.collector.questMethod;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.khasanof.collector.GenericMethodContext;
import org.khasanof.context.FluentContextHolder;
import org.khasanof.enums.HandleClasses;
import org.khasanof.enums.HandleType;
import org.khasanof.executors.matcher.CompositeMatcher;
import org.khasanof.models.invoker.SimpleInvoker;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author Nurislom
 * @see org.khasanof.collector.questMethod
 * @since 23.06.2023 23:46
 */
@Slf4j
//@Component
public class AsyncSimpleQuestMethod implements BaseSearchMethod {

    private final GenericMethodContext<HandleClasses, Map<Method, Object>> methodContext;
    private final CompositeMatcher matcher;

    public AsyncSimpleQuestMethod(GenericMethodContext<HandleClasses, Map<Method, Object>> methodContext, CompositeMatcher matcher) {
        this.methodContext = methodContext;
        this.matcher = matcher;
    }

    @Override
    public SimpleInvoker getMethodValueAnn(Object value, HandleClasses type) {
        log.info("contextHolder.getCurrentUpdate() = " + FluentContextHolder.getCurrentUpdate());
        System.out.printf("Enter type - %s, value - %s \n", type, value);
        CompletableFuture<Map.Entry<Method, Object>> supplyAsync;
        if (type.isHasSubType()) {
            supplyAsync = CompletableFuture.supplyAsync(() -> methodContext.contains(type) ?
                            methodContext.find(type).entrySet()
                                    .parallelStream().filter(aClass -> matcher.chooseMatcher(aClass.getKey(),
                                            value, type.getType()))
                                    .findFirst().orElse(null) : null)
                    .thenComposeAsync(s -> CompletableFuture.supplyAsync(() -> {
                        if (Objects.isNull(s)) {
                            return methodContext.contains(type.getSubHandleClasses()) ?
                                    methodContext.find(type.getSubHandleClasses()).entrySet()
                                            .parallelStream().filter(aClass -> matcher.chooseMatcher(aClass.getKey(),
                                                    value, type.getSubHandleClasses().getType()))
                                            .findFirst().orElse(null) : null;
                        }
                        return s;
                    }));
        } else {
            supplyAsync = CompletableFuture.supplyAsync(
                    () -> methodContext.contains(type) ?
                            methodContext.find(type).entrySet()
                                    .parallelStream().filter(aClass -> matcher.chooseMatcher(aClass.getKey(),
                                            value, type.getType()))
                                    .findFirst().orElse(null) : null);
        }
        return supplyAsync.thenApply(SearchMethodUtils::createResultInvoker).join();
    }

    @Override
    @SneakyThrows
    public SimpleInvoker getHandleAnyMethod(HandleType handleType) {
        log.info("contextHolder.getCurrentUpdate() = " + FluentContextHolder.getCurrentUpdate());
        return CompletableFuture.supplyAsync(() -> methodContext.contains(HandleClasses.HANDLE_ANY) ?
                methodContext.find(HandleClasses.HANDLE_ANY).entrySet().parallelStream().filter(
                                clazz -> matcher.chooseMatcher(clazz.getKey(), handleType))
                        .findFirst().orElse(null) : null).thenApply(SearchMethodUtils::createResultInvoker).get();
    }

    @SneakyThrows
    @Override
    public Set<SimpleInvoker> getAllHandleAnyMethod(HandleType handleType) {
        log.info("contextHolder.getCurrentUpdate() = " + FluentContextHolder.getCurrentUpdate());
        return CompletableFuture.supplyAsync(() -> methodContext.contains(HandleClasses.HANDLE_ANY) ?
                methodContext.find(HandleClasses.HANDLE_ANY).entrySet().parallelStream().filter(
                                clazz -> matcher.chooseMatcher(clazz.getKey(), handleType))
                        .map(SearchMethodUtils::createResultInvoker)
                        .collect(Collectors.toSet()) : null)
                .get();
    }

    @Override
    public boolean contains(HandleClasses handleClasses) {
        return methodContext.contains(handleClasses);
    }

}
