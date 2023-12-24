package org.khasanof.executors.determination;

import org.jetbrains.annotations.NotNull;
import org.khasanof.config.ApplicationProperties;
import org.khasanof.config.Config;
import org.khasanof.custom.ProcessTypeResolver;
import org.khasanof.enums.ProcessType;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Nurislom
 * @see org.khasanof.executors.determination
 * @since 16.07.2023 17:00
 */
@Component
public class DefaultDeterminationService implements DeterminationService, Config {

    private final Map<Integer, List<BiConsumer<Update, Set<SimpleInvoker>>>> orderListMap = new TreeMap<>();
    private final ApplicationContext applicationContext;
    private final ApplicationProperties.Bot bot;

    public DefaultDeterminationService(ApplicationContext applicationContext, ApplicationProperties properties) {
        this.applicationContext = applicationContext;
        this.bot = properties.getBot();
    }

    @Override
    public List<BiConsumer<Update, Set<SimpleInvoker>>> getDeterminations() {
        return orderListMap.values().stream().filter(Objects::nonNull)
                .flatMap(Collection::stream).collect(Collectors.toList());
    }

    @Override
    public void runnable() {
        applicationContext.getBeansOfType(DeterminationFunction.class)
                .values().stream().filter(determinationPredicate())
                .forEach(this::addDeterminationFunction);
    }

    @NotNull
    private Predicate<DeterminationFunction> determinationPredicate() {
        return orderFunction -> ProcessTypeResolver.hasAcceptProcessType(orderFunction, this.bot.getProcessType());
    }

    private void addDeterminationFunction(DeterminationFunction orderFunction) {
        if (orderListMap.containsKey(orderFunction.getOrder())) {
            List<BiConsumer<Update, Set<SimpleInvoker>>> list = orderListMap.get(orderFunction.getOrder());
            list.add(orderFunction.accept(applicationContext));
        } else {
            orderListMap.put(orderFunction.getOrder(), new ArrayList<>() {{
                add(orderFunction.accept(applicationContext));
            }});
        }
    }

    @Override
    public ProcessType processType() {
        return ProcessType.BOTH;
    }

}
