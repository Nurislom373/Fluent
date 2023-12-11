package org.khasanof.button.strategy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

/**
 * @author Nurislom
 * @see org.khasanof.button.strategy
 * @since 10/29/2023 11:52 PM
 */
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public enum InlineKeyboardStrategy {

    LIST_MAP(ListMapInlineKeyboardBuildStrategy::new),
    KEYBOARD_BUTTON_LIST(ListInlineKeyboardBuildStrategy::new),
    ROW_KEYBOARD_BUTTON_LIST(RowListInlineKeyboardsBuildStrategy::new);

    private @SuppressWarnings("rawtypes") Supplier<InlineKeyboardBuildStrategy> factory;

}
