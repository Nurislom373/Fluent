package org.khasanof.executors.execution;

import lombok.SneakyThrows;
import org.khasanof.event.MethodV1Event;
import org.khasanof.utils.MethodUtils;

/**
 * The {@link AbstractExecution} class is used to declare the methods and variables needed
 * by the classes of the {@link Execution} interface. And in this class there are also standard execution methods.
 *
 * @author Nurislom
 * @see org.khasanof.executors.execution
 * @since 8/20/2023 8:30 PM
 */
public abstract class AbstractExecution {

    /**
     * The {@link AbstractExecution#defaultExecution} method is the method that calls the simple methods.
     *
     * @param methodV1Event Which method should be called in the {@link MethodV1Event} class comes in and is called.
     */
    @SneakyThrows
    protected void defaultExecution(MethodV1Event methodV1Event) {
        Object[] objects = MethodUtils.sorterV2(methodV1Event.getInvokerModel().getArgs(),
                methodV1Event.getMethod().getParameterTypes());
        methodV1Event.getMethod().invoke(methodV1Event.getClassEntry().getValue(), objects);
    }

}
