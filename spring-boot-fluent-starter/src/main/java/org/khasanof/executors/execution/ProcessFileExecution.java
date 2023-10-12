package org.khasanof.executors.execution;

import org.khasanof.enums.additional.AdditionalParamType;
import org.khasanof.event.MethodV1Event;
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
    public void run(MethodV1Event methodV1Event) throws InvocationTargetException, IllegalAccessException {
        AdditionalParamType paramType = methodV1Event.getInvokerModel().getAdditionalParam().getType();
        Object extraParam = Arrays.stream(methodV1Event.getInvokerModel().getArgs())
                .filter(o -> o.getClass().equals(paramType.getParmaType()))
                .findFirst().orElseThrow(() -> new RuntimeException("Match object not found!"));
        int length = methodV1Event.getInvokerModel().getArgs().length;
        Object[] copy = Arrays.copyOf(methodV1Event.getInvokerModel().getArgs(), length + 1);
        copy[copy.length - 1] = extraParam;
        methodV1Event.getClassEntry().getKey().invoke(methodV1Event.getClassEntry().getValue(), copy);
    }

    @Override
    public AdditionalParamType getType() {
        return AdditionalParamType.PROCESS_FILE_PARAM;
    }

}
