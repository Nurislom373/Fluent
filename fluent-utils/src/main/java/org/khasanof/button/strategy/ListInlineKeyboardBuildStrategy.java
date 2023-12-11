package org.khasanof.button.strategy;

import org.khasanof.button.InlineKeyboardButtonBuilder;
import org.khasanof.button.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.button.strategy
 * @since 10/30/2023 12:00 AM
 */
public class ListInlineKeyboardBuildStrategy implements InlineKeyboardBuildStrategy<List<KeyboardButton>> {

    private final InlineKeyboardButtonBuilder buttonBuilder = new InlineKeyboardButtonBuilder();

    @Override
    public InlineKeyboardMarkup build(List<KeyboardButton> keyboardButtons) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> inlineKeyboardButtons = keyboardButtons.stream()
                .map(this::inlineKeyboardButtonBuilder)
                .toList();
        inlineKeyboardMarkup.setKeyboard(List.of(inlineKeyboardButtons));
        return inlineKeyboardMarkup;
    }

    private InlineKeyboardButton inlineKeyboardButtonBuilder(KeyboardButton button) {
        return buttonBuilder.builder(button);
    }

}
