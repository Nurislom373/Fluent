package org.khasanof.strategy;

import org.khasanof.RowKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.strategy
 * @since 10/30/2023 12:18 AM
 */
public class RowListInlineKeyboardsBuildStrategy implements InlineKeyboardBuildStrategy<List<RowKeyboardButton>> {

    @Override
    public InlineKeyboardMarkup build(List<RowKeyboardButton> rowKeyboardButtons) {
        throw new RuntimeException("Not Implemented!");
    }

}
