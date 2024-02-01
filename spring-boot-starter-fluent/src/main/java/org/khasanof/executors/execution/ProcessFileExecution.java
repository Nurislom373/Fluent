package org.khasanof.executors.execution;

import org.khasanof.enums.additional.AdditionalParamType;
import org.khasanof.event.ExecutionMethod;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * @author Nurislom
 * @see org.khasanof.executors.execution
 * @since 8/13/2023 9:33 PM
 */
@Component
public class ProcessFileExecution implements Execution {

    @Override
    public void run(ExecutionMethod executionMethod) throws InvocationTargetException, IllegalAccessException {
        SimpleInvoker simpleInvoker = executionMethod.getInvokerModel().getInvokerReference();
        AdditionalParamType paramType = executionMethod.getInvokerModel().getAdditionalParam().getType();

        Object extraParam = Arrays.stream(executionMethod.getInvokerModel().getArgs())
                .filter(o -> o.getClass().equals(paramType.getParmaType()))
                .findFirst().orElseThrow(() -> new RuntimeException("Match object not found!"));

        int length = executionMethod.getInvokerModel().getArgs().length;
        Object[] copy = Arrays.copyOf(executionMethod.getInvokerModel().getArgs(), length + 1);
        copy[copy.length - 1] = extraParam;
        simpleInvoker.getMethod().invoke(simpleInvoker.getReference(), copy);
    }

    @Override
    public AdditionalParamType getType() {
        return AdditionalParamType.PROCESS_FILE_PARAM;
    }
}
