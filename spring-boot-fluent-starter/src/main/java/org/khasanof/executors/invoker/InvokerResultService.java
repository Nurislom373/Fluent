package org.khasanof.executors.invoker;

import org.khasanof.enums.InvokerType;
import org.khasanof.model.InvokerMethod;
import org.khasanof.model.InvokerObject;
import org.khasanof.model.InvokerResult;
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

    public Map.Entry<Method, Object> getResultEntry(InvokerResult result) {
        if (result.getType().equals(InvokerType.CLASS)) {
            InvokerObject invokerObject = (InvokerObject) result;
            return new AbstractMap.SimpleEntry<>(MethodUtils.getClassMethodByName(invokerObject.getReference(),
                    invokerObject.getExecutionMethodName()), invokerObject.getReference());
        } else {
            InvokerMethod invokerMethod = (InvokerMethod) result;
            return new AbstractMap.SimpleEntry<>(invokerMethod.getMethod(), invokerMethod.getReference());
        }
    }

}
