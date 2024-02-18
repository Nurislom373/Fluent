package org.khasanof.collector;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.collector.loader.HandlerLoader;
import org.khasanof.factories.invoker.method.InvokerMethodFactory;
import org.khasanof.models.invoker.SimpleInvoker;
import org.khasanof.state.StateAction;
import org.khasanof.state.collector.StateValidator;
import org.khasanof.utils.MethodUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.collector
 * @since 8/19/2023 12:05 AM
 */
@Slf4j
@SuppressWarnings({"rawtypes"})
public class DefaultStateMethodContext implements StateMethodContext {

    public static final String NAME = "defaultStateMethodContext";
    public static final String METHOD_NAME = "onReceive";

    private final HandlerLoader beansLoader;
    private final StateValidator stateValidator;
    private final InvokerMethodFactory invokerMethodFactory;
    private final Map<Enum, SimpleInvoker> simpleInvokerMap = new HashMap<>();

    public DefaultStateMethodContext(HandlerLoader resourceLoader,
                                     StateValidator stateValidator,
                                     InvokerMethodFactory invokerMethodFactory) {

        this.beansLoader = resourceLoader;
        this.stateValidator = stateValidator;
        this.invokerMethodFactory = invokerMethodFactory;
    }

    @Override
    public Optional<SimpleInvoker> find(Enum key) {
        return Optional.ofNullable(simpleInvokerMap.get(key));
    }

    @Override
    public boolean contains(Enum anEnum) {
        return simpleInvokerMap.containsKey(anEnum);
    }

    @Override
    public void assembleMethods() {
        beansLoader.getHandlersOfType(StateAction.class)
                .forEach((s, stateActions) -> putState(stateActions));
        log.info("HANDLE_STATE : {}", simpleInvokerMap.size());
    }

    private void putState(StateAction stateActions) {
        if (stateValidator.valid(stateActions)) {
            putStateContext(stateActions);
        }
    }

    private void putStateContext(StateAction stateActions) {
        if (simpleInvokerMap.containsKey(stateActions.state())) {
            log.warn("this enum already used! state must be unique");
            return;
        }
        simpleInvokerMap.put(stateActions.state(), createSimpleInvoker(stateActions));
    }

    private SimpleInvoker createSimpleInvoker(StateAction stateActions) {
        return invokerMethodFactory.create(Map.entry(MethodUtils.getClassMethodByName(stateActions, METHOD_NAME), stateActions));
    }
}
