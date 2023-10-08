package org.khasanof.executors.determination;

import org.khasanof.custom.ProcessTypeResolver;
import org.khasanof.enums.ProcessType;
import org.khasanof.model.InvokerResult;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * @author Nurislom
 * @see org.khasanof.executors.determination
 * @since 16.07.2023 19:20
 */
public class DeterminationAdapter {

    public void fillMap(Map<Integer, List<BiConsumer<Update, Set<InvokerResult>>>> map,
                        ApplicationContext applicationContext, ProcessType processType) {
        applicationContext.getBeansOfType(DeterminationFunction.class)
                .values().stream()
                .filter((orderFunction -> ProcessTypeResolver.hasAcceptProcessType(orderFunction, processType)))
                .forEach((orderFunction) -> {
                    if (map.containsKey(orderFunction.getOrder())) {
                        map.get(orderFunction.getOrder()).add(orderFunction.accept(applicationContext));
                    } else {
                        map.put(orderFunction.getOrder(), new ArrayList<>() {{
                            add(orderFunction.accept(applicationContext));
                        }});
                    }
                });
    }

}
