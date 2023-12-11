package org.khasanof.button.strategy;

import org.khasanof.button.InlineKeyboardButtonBuilder;
import org.khasanof.button.RowKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Nurislom
 * @see org.khasanof.button.strategy
 * @since 10/30/2023 12:18 AM
 */
public class RowListInlineKeyboardsBuildStrategy implements InlineKeyboardBuildStrategy<List<RowKeyboardButton>> {

    private final InlineKeyboardButtonBuilder buttonBuilder = new InlineKeyboardButtonBuilder();

    @Override
    public InlineKeyboardMarkup build(List<RowKeyboardButton> rowKeyboardButtons) {
        List<List<InlineKeyboardButton>> list = new ArrayList<>();
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        Map<Integer, List<RowKeyboardButton>> collectMap = rowKeyboardButtons.stream()
                .collect(Collectors.groupingBy(RowKeyboardButton::getRow));
        Set<Integer> rows = new TreeSet<>(collectMap.keySet());
        rows.forEach(row -> list.add((row - 1), collectMap.get(row).stream()
                .map(this::buildButton).toList()));

        keyboardMarkup.setKeyboard(list);
        return keyboardMarkup;
    }

    private InlineKeyboardButton buildButton(RowKeyboardButton button) {
        return buttonBuilder.builder(button);
    }

}
