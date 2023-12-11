package org.khasanof.collector.questMethod;

import org.khasanof.collector.GenericMethodContext;
import org.khasanof.model.invoker.SimpleInvokerObject;
import org.khasanof.model.invoker.SimpleInvoker;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.collector.questMethod
 * @since 8/19/2023 2:16 PM
 */
@Component
public class StateQuestMethod implements SearchMethod<Enum> {

    private static final String executionMethodName = "onReceive";
    private final GenericMethodContext<Enum, Map.Entry<Method, Object>> methodContext;

    public StateQuestMethod(GenericMethodContext<Enum, Map.Entry<Method, Object>> methodContext) {
        this.methodContext = methodContext;
    }

    @Override
    public SimpleInvoker getMethodValueAnn(Object value, Enum param) {
        return resultCreator(methodContext.getMethodsByGenericKey(param));
    }

    @Override
    public boolean containsKey(Enum param) {
        return methodContext.containsKey(param);
    }

    private SimpleInvoker resultCreator(Map.Entry<Method, Object> entry) {
        if (Objects.isNull(entry)) {
            return null;
        }
        return new SimpleInvokerObject(entry.getValue(), executionMethodName);
    }
}
