package org.khasanof;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.khasanof.state.State;
import org.khasanof.state.StateImpl;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 10/17/2023 9:53 PM
 */
@Converter
public class StateConverter implements AttributeConverter<State, Enum> {

    @Override
    public Enum convertToDatabaseColumn(State state) {
        Objects.requireNonNull(state, "state must be not null!");
        return state.getState();
    }

    @Override
    public State convertToEntityAttribute(Enum state) {
        Objects.requireNonNull(state, "state must be not null!");
        return new StateImpl(state);
    }

}
