package org.khasanof.utils.keyboards.inline;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.utils.keyboards
 * @since 3/2/2024 11:30 PM
 */
public class InlineKeyboardMarkupBuilder {

    private final List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
    private List<InlineKeyboardButton> currentRow = new ArrayList<>();

    public InlineKeyboardMarkupBuilder() {
        keyboard.add(currentRow);
    }

    /**
     *
     * @return
     */
    public InlineKeyboardBuilder addButton() {
        return addButton(null);
    }

    /**
     *
     * @param text
     * @return
     */
    public InlineKeyboardBuilder addButton(String text) {
        return addButton(text, null);
    }

    /**
     *
     * @param text
     * @param callbackData
     * @return
     */
    public InlineKeyboardBuilder addButton(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setCallbackData(callbackData);
        button.setText(text);

        currentRow.add(button);
        return new InlineKeyboardBuilder(button);
    }

    /**
     *
     * @return
     */
    public InlineKeyboardMarkupBuilder addRow() {
        currentRow = new ArrayList<>();
        keyboard.add(currentRow);
        return this;
    }

    /**
     *
     * @return
     */
    public InlineKeyboardMarkup build() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }
}
