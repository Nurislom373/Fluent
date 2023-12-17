package org.khasanof.models.invoker;

import org.khasanof.enums.InvokerType;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.models.invoker
 * @since 8/19/2023 6:19 PM
 */
public interface SimpleInvoker {

    Method getMethod();

    Object getReference();

    InvokerType getType();

}
