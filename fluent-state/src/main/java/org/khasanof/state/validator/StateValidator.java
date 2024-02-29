package org.khasanof.state.validator;

import org.khasanof.state.StateAction;

/**
 * @author Nurislom
 * @see org.khasanof.state.validator
 * @since 2/29/2024 9:28 PM
 */
public interface StateValidator {

    /**
     *
     * @param stateAction
     * @return
     */
    boolean valid(StateAction stateAction);
}
