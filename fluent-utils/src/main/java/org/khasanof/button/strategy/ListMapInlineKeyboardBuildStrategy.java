package org.khasanof.button.strategy;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.button.strategy
 * @since 10/29/2023 11:51 PM
 */
public class ListMapInlineKeyboardBuildStrategy implements InlineKeyboardBuildStrategy<List<Map<String, String>>> {

    @Override
    public InlineKeyboardMarkup build(List<Map<String, String>> maps) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> listList = maps.stream()
                .map(innerMap -> innerMap.entrySet()
                        .stream().map(this::buildButton).toList())
                .toList();
        keyboardMarkup.setKeyboard(listList);
        return keyboardMarkup;
    }

    private InlineKeyboardButton buildButton(Map.Entry<String, String> button) {
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(button.getKey());
        inlineKeyboardButton.setCallbackData(button.getValue());
        return inlineKeyboardButton;
    }

}
