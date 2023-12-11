package org.khasanof.executors.determination;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.collector.Collector;
import org.khasanof.collector.SimpleCollector;
import org.khasanof.condition.Condition;
import org.khasanof.enums.HandleType;
import org.khasanof.enums.ProcessType;
import org.khasanof.executors.HandleFunctionsMatcher;
import org.khasanof.model.invoker.SimpleInvoker;
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
 * @since 16.07.2023 18:58
 */
@Slf4j
@Component(HandleUpdateFunction.NAME)
public class HandleUpdateFunction implements DeterminationFunction {

    public static final String NAME = "handleUpdateFunction";

    @Override
    @SuppressWarnings("unchecked")
    public BiConsumer<Update, Set<SimpleInvoker>> accept(ApplicationContext applicationContext) {
        return ((update, invokerResults) -> {
            HandleFunctionsMatcher anyFunctionMatcher = applicationContext.getBean(HandleFunctionsMatcher.class);
            Optional<Map.Entry<HandleType, Object>> optional = anyFunctionMatcher.matchFunctions(update);

            optional.ifPresent((handleTypeObjectEntry -> {
                if (HandleType.hasHandleAnnotation(handleTypeObjectEntry.getKey())) {
                    Collector<Class<? extends Annotation>> collector = applicationContext.getBean(SimpleCollector.NAME, Collector.class);

                    SimpleInvoker classEntry = collector.getInvokerResult(handleTypeObjectEntry.getValue(),
                            handleTypeObjectEntry.getKey().getHandleClasses().getType());

                    Condition.isTrue(Objects.nonNull(classEntry))
                            .thenCall(() -> {
                                invokerResults.add(classEntry);
                            })
                            .elseCall(() -> log.warn("Method not found!"));
                }
            }));
        });
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
