package org.khasanof.executors.determination;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.collector.Collector;
import org.khasanof.collector.SimpleCollector;
import org.khasanof.converter.HandleTypeConverter;
import org.khasanof.enums.HandleClasses;
import org.khasanof.enums.ProcessType;
import org.khasanof.executors.appropriate.determining.AppropriateDetermining;
import org.khasanof.models.executors.AppropriateMethod;
import org.khasanof.models.invoker.SimpleInvoker;
import org.khasanof.utils.HandleTypeUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * @author Nurislom
 * @see org.khasanof.executors.determination
 * @since 16.07.2023 18:58
 */
@Slf4j
@SuppressWarnings("unchecked")
@Component(HandleUpdateFunction.NAME)
public class HandleUpdateFunction implements DeterminationFunction {

    public static final String NAME = "handleUpdateFunction";

    @Override
    public BiConsumer<Update, Set<SimpleInvoker>> accept(ApplicationContext applicationContext) {
        return ((update, invokerResults) -> {
            var appropriateDetermining = applicationContext.getBean(AppropriateDetermining.class);

            appropriateDetermining.determining(update)
                    .ifPresentOrElse(appropriateMethod -> internalAccept(applicationContext, invokerResults, appropriateMethod),
                            () ->log.warn("Method not found!"));
        });
    }

    private void internalAccept(ApplicationContext applicationContext, Set<SimpleInvoker> invokerResults, AppropriateMethod appropriateMethod) {
        if (HandleTypeUtils.isConvertHandleClass(appropriateMethod.getHandleType())) {
            addInvokersToResultSet(applicationContext, invokerResults, appropriateMethod);
        }
    }

    private void addInvokersToResultSet(ApplicationContext applicationContext, Set<SimpleInvoker> invokerResults, AppropriateMethod appropriateMethod) {
        Collector<Class<? extends Annotation>> collector = applicationContext.getBean(SimpleCollector.NAME, Collector.class);
        HandleTypeConverter converter = applicationContext.getBean(HandleTypeConverter.class);

        HandleClasses handleClasses = converter.fromConvert(appropriateMethod.getHandleType());
        collector.getInvokerResult(appropriateMethod.getValue(), handleClasses.getType())
                .ifPresentOrElse(invokerResults::add, () -> log.warn("Method not found!"));
    }

    @Override
    public Integer getOrder() {
        return 10;
    }

    @Override
    public ProcessType processType() {
        return ProcessType.UPDATE;
    }
}
