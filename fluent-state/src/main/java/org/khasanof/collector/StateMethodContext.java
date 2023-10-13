package org.khasanof.collector;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.collector.loader.ResourceLoader;
import org.khasanof.state.StateAction;
import org.khasanof.state.collector.StateValidator;
import org.khasanof.utils.MethodUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.collector
 * @since 8/19/2023 12:05 AM
 */
@Slf4j
@Component(StateMethodContext.NAME)
public class StateMethodContext implements GenericMethodContext<Enum, Map.Entry<Method, Object>>, AssembleMethods {

    public static final String NAME = "stateMethodContext";

    private final ResourceLoader resourceLoader;
    private final StateValidator stateValidator;
    private final Map<Enum, Map.Entry<Method, Object>> invokerMethodsMap = new HashMap<>();

    public StateMethodContext(ResourceLoader resourceLoader, StateValidator stateValidator) {
        this.resourceLoader = resourceLoader;
        this.stateValidator = stateValidator;
    }

    @Override
    public Map.Entry<Method, Object> getMethodsByGenericKey(Enum key) {
        return invokerMethodsMap.get(key);
    }

    @Override
    public boolean containsKey(Enum anEnum) {
        return invokerMethodsMap.containsKey(anEnum);
    }

    @Override
    public void assembleMethods() {
        resourceLoader.getBeansOfType(StateAction.class).forEach((s, stateActions) -> {
            if (stateValidator.valid(stateActions)) {
                if (invokerMethodsMap.containsKey(stateActions.state())) {
                    log.warn("this enum already used! state must be unique");
                } else {
                    invokerMethodsMap.put(stateActions.state(),
                            Map.entry(MethodUtils.getClassMethodByName(stateActions, "onReceive"),
                                    stateActions));
                }
            }
        });
        log.info("HANDLE_STATE : {}", invokerMethodsMap.size());
    }
}
