package org.khasanof.executors.invoker;

import org.khasanof.model.InvokerModelV2;
import org.khasanof.model.InvokerResult;

/**
 * @author Nurislom
 * @see org.khasanof.executors.invoker
 * @since 10/13/2023 11:40 PM
 */
public interface InvokerFunctions {

    void add(InvokerModelV2 modelV2);

    InvokerModelV2 findByName(String name);

    InvokerModelV2 fillAndGet(InvokerResult result, Object... args);

}
