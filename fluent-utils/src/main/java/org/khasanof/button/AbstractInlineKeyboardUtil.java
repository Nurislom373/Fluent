package org.khasanof.button;

import org.khasanof.button.flyweight.InlineKeyboardBuildStrategyFlyWeight;
import org.khasanof.button.strategy.InlineKeyboardStrategy;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 10/29/2023 10:57 PM
 */
public abstract class AbstractInlineKeyboardUtil implements InlineKeyboardUtil {

    private final InlineKeyboardBuildStrategyFlyWeight buildStrategyFlyWeight;

    protected AbstractInlineKeyboardUtil(InlineKeyboardBuildStrategyFlyWeight buildStrategyFlyWeight) {
        this.buildStrategyFlyWeight = buildStrategyFlyWeight;
    }

    @Override
    public InlineKeyboardMarkup createWithListMap(List<Map<String, String>> keyboards) {
        return refBuild(keyboards, InlineKeyboardStrategy.LIST_MAP);
    }

    @Override
    public InlineKeyboardMarkup createWithListMap(List<Map<String, String>> keyboards, ValueType type) {
        return null;
    }

    @Override
    public InlineKeyboardMarkup createWithKeyboardButtons(List<KeyboardButton> keyboards) {
        return refBuild(keyboards, InlineKeyboardStrategy.KEYBOARD_BUTTON_LIST);
    }

    @Override
    public InlineKeyboardMarkup createWithRowKeyboardButtons(List<RowKeyboardButton> keyboards) {
        return refBuild(keyboards, InlineKeyboardStrategy.ROW_KEYBOARD_BUTTON_LIST);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private InlineKeyboardMarkup refBuild(List keyboards, InlineKeyboardStrategy strategy) {
        return buildStrategyFlyWeight.keyboardBuildStrategyFactory(strategy).build(keyboards);
    }

}
