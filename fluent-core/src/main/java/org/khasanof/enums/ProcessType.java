package org.khasanof.enums;

import lombok.RequiredArgsConstructor;

/**
 * @author Nurislom
 * @see org.khasanof.enums
 * @since 09.07.2023 18:28
 */
@RequiredArgsConstructor
public enum ProcessType {

    /**
     *
     */
    BOTH,

    /**
     * State ProcessType only works after state dependency is added.
     */
    STATE,

    /**
     *
     */
    UPDATE;
}
