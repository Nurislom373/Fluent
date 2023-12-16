package org.khasanof.factories.invoker;

import org.khasanof.models.invoker.SimpleInvoker;
import org.khasanof.models.invoker.SimpleInvokerMethod;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.factories.invoker
 * @since 12/16/2023 9:14 PM
 */
@Component
public class DefaultInvokerMethodFactory implements InvokerMethodFactory {

    @Override
    public SimpleInvoker create(Map.Entry<Method, Object> methodObjectEntry) {
        return new SimpleInvokerMethod(methodObjectEntry.getKey(), methodObjectEntry.getValue());
    }

}
