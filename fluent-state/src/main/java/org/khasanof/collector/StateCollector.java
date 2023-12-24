package org.khasanof.collector;

import org.khasanof.collector.questMethod.SearchMethod;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.collector
 * @since 8/19/2023 2:11 PM
 */
@Component(StateCollector.NAME)
@SuppressWarnings({"unchecked", "rawtypes"})
public class StateCollector extends AbstractCollector implements Collector<Enum>{

    public static final String NAME = "stateCollector";

    public StateCollector(SearchMethod<Enum> questMethod) {
        super(questMethod);
    }

    @Override
    public Optional<SimpleInvoker> getInvokerResult(Object value, Enum param) {
        return questMethod.getMethodValueAnn(value, param);
    }

    @Override
    public boolean hasHandle(Enum param) {
        return questMethod.containsKey(param);
    }

}
