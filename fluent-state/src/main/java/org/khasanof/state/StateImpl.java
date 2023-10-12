package org.khasanof.state;

import lombok.AllArgsConstructor;

/**
 * @author Nurislom
 * @see org.khasanof.state
 * @since 8/20/2023 5:26 PM
 */
@AllArgsConstructor
public class StateImpl implements State {

    private Enum state;

    @Override
    public Enum getState() {
        return state;
    }

    @Override
    public void nextState() {
        state = enumNextValue(state.getClass(), state);
    }

    @Override
    public void nextState(Enum state) {
        this.state = state;
    }

    private Enum enumNextValue(Class<? extends Enum> enumType, Enum currentState) {
        boolean isCurrent = false;
        for (Enum enumConstant : enumType.getEnumConstants()) {
            if (isCurrent) {
                return enumConstant;
            }
            if (enumConstant.equals(currentState)) {
                isCurrent = true;
            }
        }
        throw new RuntimeException("next state not found!");
    }

}
