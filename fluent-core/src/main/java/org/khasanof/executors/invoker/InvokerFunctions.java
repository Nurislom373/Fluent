package org.khasanof.executors.invoker;

import org.khasanof.model.SampleModel;
import org.khasanof.model.InvokerResult;

/**
 * @author Nurislom
 * @see org.khasanof.executors.invoker
 * @since 10/13/2023 11:40 PM
 */
public interface InvokerFunctions {

    void add(SampleModel modelV2);

    SampleModel findByName(String name);

    SampleModel fillAndGet(InvokerResult result, Object... args);

}
