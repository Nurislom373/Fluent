package org.khasanof.factories.invoker.method;

import org.khasanof.factories.GenericFactory;
import org.khasanof.models.invoker.SimpleInvoker;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.factories.invoker
 * @since 12/16/2023 9:09 PM
 */
public interface InvokerMethodFactory extends GenericFactory<Map.Entry<Method, Object>, SimpleInvoker> {
}
