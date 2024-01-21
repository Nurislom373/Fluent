package org.khasanof.executors.execution;

import org.khasanof.event.ExecutionMethod;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Nurislom
 * @see org.khasanof.executors.execution
 * @since 8/13/2023 9:28 PM
 */
@Component
public class SimpleExecution extends AbstractExecution {

    @Override
    public void run(ExecutionMethod methodV1Event) throws InvocationTargetException, IllegalAccessException {
        defaultExecution(methodV1Event);
    }
}
