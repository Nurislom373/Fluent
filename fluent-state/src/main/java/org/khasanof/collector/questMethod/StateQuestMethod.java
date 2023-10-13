package org.khasanof.collector.questMethod;

import org.khasanof.collector.GenericMethodContext;
import org.khasanof.model.InvokerObject;
import org.khasanof.model.InvokerResult;
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
public class StateQuestMethod implements QuestMethod<Enum> {

    private final GenericMethodContext<Enum, Map.Entry<Method, Object>> methodContext;

    public StateQuestMethod(GenericMethodContext<Enum, Map.Entry<Method, Object>> methodContext) {
        this.methodContext = methodContext;
    }

    @Override
    public InvokerResult getMethodValueAnn(Object value, Enum param) {
        return resultCreator(methodContext.getMethodsByGenericKey(param));
    }

    @Override
    public boolean containsKey(Enum param) {
        return methodContext.containsKey(param);
    }

    private InvokerResult resultCreator(Map.Entry<Method, Object> entry) {
        if (Objects.isNull(entry)) {
            return null;
        }
        return new InvokerObject(entry.getValue(), "onReceive");
    }
}
