package org.khasanof.state.collector;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.GenericContains;
import org.khasanof.collector.GenericMethodContext;
import org.khasanof.exceptions.InvalidValidationException;
import org.khasanof.state.StateAction;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.state
 * @since 8/19/2023 12:31 AM
 */
@Slf4j
@Component
@SuppressWarnings({"rawtypes"})
public class StateValidator {

    private final StateConfigCollector stateConfigCollector;

    public StateValidator(StateConfigCollector stateConfigCollector) {
        this.stateConfigCollector = stateConfigCollector;
    }

    public boolean valid(StateAction stateActions) {
        if (Objects.isNull(stateActions.state())) {
            log.warn("state must not be null in this class : {}", stateActions.getClass());
            throw new InvalidValidationException("state must not be null!");
        }
        if (!stateConfigCollector.contains(stateActions.state())) {
            log.warn("An unregistered enum type was introduced! : {}", stateActions.getClass());
            throw new RuntimeException("An unregistered enum type was introduced");
        }
        return true;
    }

}
