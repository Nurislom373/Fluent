package org.khasanof.state;

/**
 * @author Nurislom
 * @see org.khasanof.state
 * @since 10/27/2023 11:15 PM
 */
public interface StateBuilder {

    static State create(Enum state) {
        return new StateImpl(state);
    }

    static State create(Enum state, Long userId) {
        return new StateWithId(state, userId);
    }

}
