package org.khasanof.executors.invoker;

import org.khasanof.models.Invoker;
import org.khasanof.models.invoker.SimpleInvoker;

/**
 * @author Nurislom
 * @see org.khasanof.executors.invoker
 * @since 12/17/2023 5:50 PM
 */
public interface InvokerFunctionsAdaptee {

    Invoker adaptee(SimpleInvoker simpleInvoker, Object... args);

}
