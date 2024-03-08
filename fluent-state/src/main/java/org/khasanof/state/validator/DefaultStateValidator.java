package org.khasanof.state.validator;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.exceptions.InvalidValidationException;
import org.khasanof.state.StateAction;
import org.khasanof.state.collector.StateConfigCollector;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.state
 * @since 8/19/2023 12:31 AM
 */
@Slf4j
@SuppressWarnings({"rawtypes"})
public class DefaultStateValidator implements StateValidator {

    private final StateConfigCollector stateConfigCollector;

    public DefaultStateValidator(StateConfigCollector stateConfigCollector) {
        this.stateConfigCollector = stateConfigCollector;
    }

    @Override
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
