package org.khasanof.enums.additional;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.khasanof.state.State;

import java.io.InputStream;
import java.util.HashMap;

/**
 * @author Nurislom
 * @see org.khasanof.enums.additional
 * @since 8/13/2023 6:53 PM
 */
@Getter
@RequiredArgsConstructor
public enum AdditionalParamType {

    STATE_PARAM(State.class),
    VAR_EXPRESSION_PARAM(HashMap.class),
    PROCESS_FILE_PARAM(InputStream.class);

    private final Class<?> parmaType;
}
