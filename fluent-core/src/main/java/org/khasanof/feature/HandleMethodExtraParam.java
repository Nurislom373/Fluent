package org.khasanof.feature;

import org.khasanof.feature.method.MethodType;
import org.khasanof.models.invoker.SimpleInvoker;

/**
 * @author Nurislom
 * @see org.khasanof.feature
 * @since 2/1/2024 10:22 PM
 */
public interface HandleMethodExtraParam {

    /**
     *
     * @param simpleInvoker
     */
    void execute(SimpleInvoker simpleInvoker);

    /**
     *
     * @return
     */
    MethodType methodType();
}
