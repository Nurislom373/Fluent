package org.khasanof.flyweight;

import org.khasanof.strategy.InlineKeyboardBuildStrategy;
import org.khasanof.strategy.InlineKeyboardStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.flyweight
 * @since 10/30/2023 12:03 AM
 */
public class InlineKeyboardBuildStrategyFlyWeight {

    @SuppressWarnings("rawtypes")
    private final Map<InlineKeyboardStrategy, InlineKeyboardBuildStrategy> buildStrategyMap = new HashMap<>();

    @SuppressWarnings("rawtypes")
    public InlineKeyboardBuildStrategy keyboardBuildStrategyFactory(InlineKeyboardStrategy strategy) {
        InlineKeyboardBuildStrategy inlineKeyboardBuildStrategy = buildStrategyMap.get(strategy);
        if (Objects.isNull(inlineKeyboardBuildStrategy)) {
            inlineKeyboardBuildStrategy = strategy.getFactory().get();
            buildStrategyMap.put(strategy, inlineKeyboardBuildStrategy);
        }
        return inlineKeyboardBuildStrategy;
    }

}
