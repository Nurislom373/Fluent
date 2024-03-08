package org.khasanof.utils;

import org.khasanof.models.invoker.InvokerParam;
import org.khasanof.models.invoker.SimpleInvoker;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.utils
 * @since 2/29/2024 12:34 AM
 */
public abstract class InvokerUtils {

    /**
     *
     * @param invoker
     * @return
     */
    public static boolean hasCondition(SimpleInvoker invoker) {
        return Objects.nonNull(invoker.getParams()) && invoker.getParams().containsKey(InvokerParam.CONDITIONS);
    }
}
