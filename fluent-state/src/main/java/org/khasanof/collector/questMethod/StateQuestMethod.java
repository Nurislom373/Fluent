package org.khasanof.collector.questMethod;

import org.khasanof.collector.GenericMethodContext;
import org.khasanof.factories.invoker.method.InvokerMethodFactory;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.collector.questMethod
 * @since 8/19/2023 2:16 PM
 */
@Component
@SuppressWarnings({"rawtypes"})
public class StateQuestMethod implements SearchMethod<Enum> {

    private final InvokerMethodFactory invokerMethodFactory;
    private final GenericMethodContext<Enum, Map.Entry<Method, Object>> methodContext;

    public StateQuestMethod(InvokerMethodFactory invokerMethodFactory, GenericMethodContext<Enum, Map.Entry<Method, Object>> methodContext) {
        this.invokerMethodFactory = invokerMethodFactory;
        this.methodContext = methodContext;
    }

    @Override
    public Optional<SimpleInvoker> getMethodValueAnn(Object value, Enum param) {
        return methodContext.find(param).map(invokerMethodFactory::create);
    }

    @Override
    public boolean containsKey(Enum param) {
        return methodContext.contains(param);
    }

}
