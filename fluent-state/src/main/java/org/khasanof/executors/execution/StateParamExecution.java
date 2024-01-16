package org.khasanof.executors.execution;

import org.khasanof.enums.additional.AdditionalParamType;
import org.khasanof.event.ExecutionMethod;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Nurislom
 * @see org.khasanof.executors.execution
 * @since 8/20/2023 5:17 PM
 */
@Component
public class StateParamExecution extends AbstractExecution implements Execution {

    @Override
    public void run(ExecutionMethod methodV1Event) throws IllegalAccessException, InvocationTargetException {
        defaultExecution(methodV1Event);
    }

    @Override
    public AdditionalParamType getType() {
        return AdditionalParamType.STATE_PARAM;
    }
}
