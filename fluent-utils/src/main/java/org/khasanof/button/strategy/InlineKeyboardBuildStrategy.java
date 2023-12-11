package org.khasanof.button.strategy;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * @author Nurislom
 * @see org.khasanof.button.strategy
 * @since 10/29/2023 11:50 PM
 */
public interface InlineKeyboardBuildStrategy<T> {

    InlineKeyboardMarkup build(T t);

}
