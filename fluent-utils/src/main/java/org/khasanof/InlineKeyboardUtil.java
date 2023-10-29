package org.khasanof;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 10/29/2023 10:52 PM
 */
public interface InlineKeyboardUtil {

    InlineKeyboardMarkup createWithListMap(List<Map<String, String>> keyboards);

    InlineKeyboardMarkup createWithListMap(List<Map<String, String>> keyboards, ValueType type);

    InlineKeyboardMarkup createWithKeyboardButtons(List<KeyboardButton> keyboards);

    InlineKeyboardMarkup createWithRowKeyboardButtons(List<RowKeyboardButton> keyboards);

}
