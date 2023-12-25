package org.khasanof.executors.determination;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.annotation.methods.HandleAny;
import org.khasanof.collector.Collector;
import org.khasanof.collector.SimpleCollector;
import org.khasanof.custom.FluentContext;
import org.khasanof.enums.HandleType;
import org.khasanof.enums.Proceed;
import org.khasanof.enums.ProcessType;
import org.khasanof.executors.HandleFunctionsMatcher;
import org.khasanof.executors.appropriate.determining.AppropriateDetermining;
import org.khasanof.models.executors.AppropriateMethod;
import org.khasanof.models.invoker.SimpleInvokerMethod;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * @author Nurislom
 * @see org.khasanof.executors.determination
 * @since 16.07.2023 19:13
 */
@Slf4j
@SuppressWarnings({"unchecked", "rawtypes"})
@Component(HandleAnyFunction.NAME)
public class HandleAnyFunction implements DeterminationFunction {

    public static final String NAME = "handleAnyFunction";

    @Override
    public BiConsumer<Update, Set<SimpleInvoker>> accept(ApplicationContext applicationContext) {
        return ((update, invokerResults) -> {
            var collector = applicationContext.getBean(SimpleCollector.NAME, Collector.class);

            if (collector.hasHandle(HandleAny.class)) {
                internalAccept(applicationContext, update, invokerResults, collector);
            }
        });
    }

    private void internalAccept(ApplicationContext applicationContext, Update update, Set<SimpleInvoker> invokerResults, Collector collector) {
        var appropriateDetermining = applicationContext.getBean(AppropriateDetermining.class);

        appropriateDetermining.determining(update)
                .ifPresentOrElse(appropriateMethod -> foundMethodsAddInvokers(invokerResults, collector, appropriateMethod),
                        () -> log.warn("HandleType not found!"));
    }

    private void foundMethodsAddInvokers(Set<SimpleInvoker> invokerResults, Collector collector, AppropriateMethod appropriateMethod) {
        var allHandleAnyMethods = collector.getAllHandleAnyMethod(appropriateMethod.getHandleType());

        if (Objects.isNull(allHandleAnyMethods)) {
            return;
        }

        invokerResults.addAll(allHandleAnyMethods);
        isCanProcess(allHandleAnyMethods);
    }

    private void isCanProcess(Set<SimpleInvoker> allHandleAnyMethods) {
        if (hasValueNotProceedInMethods(allHandleAnyMethods)) {
            FluentContext.determinationServiceBoolean.set(true);
        }
    }

    private boolean hasValueNotProceedInMethods(Set<SimpleInvoker> methods) {
        return methods.stream()
                .map(SimpleInvoker::getMethod)
                .anyMatch(method -> method.getAnnotation(HandleAny.class).proceed().equals(Proceed.NOT_PROCEED));
    }

    @Override
    public Integer getOrder() {
        return 1;
    }

    @Override
    public ProcessType processType() {
        return ProcessType.BOTH;
    }
}
