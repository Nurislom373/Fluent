package org.khasanof.executors.invoker;

import org.khasanof.model.InvokerModel;
import org.khasanof.model.InvokerModelV2;

/**
 * @author Nurislom
 * @see org.khasanof.executors
 * @since 04.07.2023 22:22
 */
public interface Invoker {

    /**
     *
     * @param invokerModel
     */
    @Deprecated
    void invoke(InvokerModel invokerModel);

    /**
     *
     * @param invokerModelV2
     */
    void invokeV2(InvokerModelV2 invokerModelV2);

}
