package org.khasanof.executors.invoker;

import org.khasanof.model.InvokerModel;
import org.khasanof.model.InvokerResult;

/**
 * @author Nurislom
 * @see org.khasanof.executors.invoker
 * @since 10/13/2023 11:40 PM
 */
public interface InvokerFunctions {

    void add(InvokerModel modelV2);

    InvokerModel findByName(String name);

    InvokerModel fillAndGet(InvokerResult result, Object... args);

}
