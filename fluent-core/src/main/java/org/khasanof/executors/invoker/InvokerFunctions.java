package org.khasanof.executors.invoker;

import org.khasanof.models.Invoker;
import org.khasanof.models.invoker.SimpleInvoker;

/**
 * @author Nurislom
 * @see org.khasanof.executors.invoker
 * @since 10/13/2023 11:40 PM
 */
public interface InvokerFunctions {

    void add(Invoker modelV2);

    Invoker findByName(String name);

    Invoker fillAndGet(SimpleInvoker result, Object... args);

}
