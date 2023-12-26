package org.khasanof.collector;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.collector.loader.HandlerLoader;
import org.khasanof.state.StateAction;
import org.khasanof.state.collector.StateValidator;
import org.khasanof.utils.MethodUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
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
@Component(StateMethodContext.NAME)
public class StateMethodContext implements GenericMethodContext<Enum, Map.Entry<Method, Object>>, AssembleMethods {

    public static final String NAME = "stateMethodContext";
    public static final String METHOD_NAME = "onReceive";

    private final HandlerLoader beansLoader;
    private final StateValidator stateValidator;
    private final Map<Enum, Map.Entry<Method, Object>> invokerMethodsMap = new HashMap<>();

    public StateMethodContext(HandlerLoader resourceLoader, StateValidator stateValidator) {
        this.beansLoader = resourceLoader;
        this.stateValidator = stateValidator;
    }

    @Override
    public Optional<Map.Entry<Method, Object>> find(Enum key) {
        return Optional.ofNullable(invokerMethodsMap.get(key));
    }

    @Override
    public boolean contains(Enum anEnum) {
        return invokerMethodsMap.containsKey(anEnum);
    }

    @Override
    public void assembleMethods() {
        beansLoader.getBeansOfType(StateAction.class).forEach((s, stateActions) -> putState(stateActions));
        log.info("HANDLE_STATE : {}", invokerMethodsMap.size());
    }

    private void putState(StateAction stateActions) {
        if (stateValidator.valid(stateActions)) {
            putStateContext(stateActions);
        }
    }

    private void putStateContext(StateAction stateActions) {
        if (invokerMethodsMap.containsKey(stateActions.state())) {
            log.warn("this enum already used! state must be unique");
        } else {
            invokerMethodsMap.put(stateActions.state(),
                    Map.entry(MethodUtils.getClassMethodByName(stateActions, METHOD_NAME), stateActions));
        }
    }

}
