package org.khasanof.state.collector;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.GenericContains;
import org.khasanof.exceptions.InvalidValidationException;
import org.khasanof.state.StateAction;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.state
 * @since 8/19/2023 12:31 AM
 */
@Slf4j
@Component
public class StateValidator {

    private final GenericContains<Enum> objectContains;

    public StateValidator(GenericContains<Enum> objectContains) {
        this.objectContains = objectContains;
    }

    public boolean valid(StateAction stateActions) {
        if (Objects.isNull(stateActions.state())) {
            log.warn("state must not be null in this class : {}", stateActions.getClass());
            throw new InvalidValidationException("state must not be null!");
        }
        if (!objectContains.contains(stateActions.state())) {
            log.warn("An unregistered enum type was introduced! : {}", stateActions.getClass());
            throw new RuntimeException("An unregistered enum type was introduced");
        }
        return true;
    }

}
