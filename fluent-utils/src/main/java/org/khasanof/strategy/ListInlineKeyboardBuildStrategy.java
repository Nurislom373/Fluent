package org.khasanof.strategy;

import org.khasanof.InlineKeyboardButtonBuilder;
import org.khasanof.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nurislom
 * @see org.khasanof.strategy
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
