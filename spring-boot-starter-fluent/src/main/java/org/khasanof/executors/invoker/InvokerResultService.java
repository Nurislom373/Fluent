package org.khasanof.executors.invoker;

import org.khasanof.enums.InvokerType;
import org.khasanof.models.invoker.SimpleInvokerMethod;
import org.khasanof.models.invoker.SimpleInvokerObject;
import org.khasanof.models.invoker.SimpleInvoker;
import org.khasanof.utils.MethodUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.executors.invoker
 * @since 8/20/2023 3:49 PM
 */
@Component
public class InvokerResultService {

    public Map.Entry<Method, Object> getResultEntry(SimpleInvoker result) {
        if (result.getType().equals(InvokerType.CLASS)) {
            SimpleInvokerObject invokerObject = (SimpleInvokerObject) result;
            return new AbstractMap.SimpleEntry<>(invokerObject.getMethod(), invokerObject.getReference());
        } else {
            SimpleInvokerMethod invokerMethod = (SimpleInvokerMethod) result;
            return new AbstractMap.SimpleEntry<>(invokerMethod.getMethod(), invokerMethod.getReference());
        }
    }

}
