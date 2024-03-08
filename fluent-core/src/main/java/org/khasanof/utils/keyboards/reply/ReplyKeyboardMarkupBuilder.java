package org.khasanof.utils.keyboards.reply;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.utils.keyboards.reply
 * @since 3/3/2024 12:06 AM
 */
public class ReplyKeyboardMarkupBuilder {

    private Boolean oneTimeKeyboard = Boolean.FALSE;
    private Boolean resizeKeyboard = Boolean.FALSE;

    private KeyboardRow currentRow = new KeyboardRow();
    private final List<KeyboardRow> keyboard = new ArrayList<>();


    public ReplyKeyboardMarkupBuilder() {
        keyboard.add(currentRow);
    }

    /**
     *
     * @return
     */
    public KeyboardButtonBuilder addButton() {
        return addButton(null);
    }

    /**
     *
     * @param text
     * @return
     */
    public KeyboardButtonBuilder addButton(String text) {
        KeyboardButton button = new KeyboardButton();
        button.setText(text);
        currentRow.add(button);
        return new KeyboardButtonBuilder(button);
    }

    /**
     *
     * @return
     */
    public ReplyKeyboardMarkupBuilder addRow() {
        currentRow = new KeyboardRow();
        keyboard.add(currentRow);
        return this;
    }

    /**
     *
     * @param oneTimeKeyboard
     * @return
     */
    public ReplyKeyboardMarkupBuilder oneTimeKeyboard(Boolean oneTimeKeyboard) {
        this.oneTimeKeyboard = oneTimeKeyboard;
        return this;
    }

    /**
     *
     * @param resizeKeyboard
     * @return
     */
    public ReplyKeyboardMarkupBuilder resizeKeyboard(Boolean resizeKeyboard) {
        this.resizeKeyboard = resizeKeyboard;
        return this;
    }

    /**
     *
     * @return
     */
    public ReplyKeyboardMarkup build() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setOneTimeKeyboard(this.oneTimeKeyboard);
        markup.setResizeKeyboard(this.resizeKeyboard);
        markup.setKeyboard(this.keyboard);
        return markup;
    }
}
