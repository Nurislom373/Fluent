package org.khasanof;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 10/29/2023 11:32 PM
 */
public class InlineKeyboardButtonBuilder {

    public InlineKeyboardButton builder(KeyboardButton button) {
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        if (Objects.isNull(button.getText())) {
            throw new RuntimeException("KeyboardButton text must not be null!");
        }
        inlineKeyboardButton.setText(button.getText());
        if (Objects.nonNull(button.getCallbackData())) {
            inlineKeyboardButton.setCallbackData(button.getCallbackData());
        }
        if (Objects.nonNull(button.getUrl())) {
            inlineKeyboardButton.setUrl(button.getUrl());
        }
        return inlineKeyboardButton;
    }

}
