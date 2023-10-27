package org.khasanof.executors.invoker;

import org.khasanof.model.InvokerModel;

/**
 * @author Nurislom
 * @see org.khasanof.executors
 * @since 04.07.2023 22:22
 */
public interface Invoker {
    /**
     *
     * @param invokerModelV2
     */
    void invoke(InvokerModel invokerModelV2);

}
