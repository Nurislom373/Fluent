package org.khasanof.state;

import lombok.Getter;

/**
 * @author Nurislom
 * @see org.khasanof.state
 * @since 11/17/2023 10:38 PM
 */
@Getter
public class StateWithId extends StateImpl {

    private Long userId;

    public StateWithId(Enum state, Long userId) {
        super(state);
        this.userId = userId;
    }

}
