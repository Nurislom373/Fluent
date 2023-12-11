package org.khasanof.collector;

import org.khasanof.collector.questMethod.SearchMethod;
import org.khasanof.model.invoker.SimpleInvoker;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof.collector
 * @since 8/19/2023 2:11 PM
 */
@Component(StateCollector.NAME)
public class StateCollector extends AbstractCollector implements Collector<Enum>{

    public static final String NAME = "stateCollector";

    public StateCollector(SearchMethod<Enum> questMethod) {
        super(questMethod);
    }

    @Override
    @SuppressWarnings("unchecked")
    public SimpleInvoker getInvokerResult(Object value, Enum param) {
        return questMethod.getMethodValueAnn(value, param);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean hasHandle(Enum param) {
        return questMethod.containsKey(param);
    }
}
