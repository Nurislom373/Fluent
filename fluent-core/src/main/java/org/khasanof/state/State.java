package org.khasanof.state;

/**
 * @author Nurislom
 * @see org.khasanof.state
 * @since 12.07.2023 22:07
 */
public interface State {

    Enum getState();

    void nextState();

    void nextState(Enum state);
}
