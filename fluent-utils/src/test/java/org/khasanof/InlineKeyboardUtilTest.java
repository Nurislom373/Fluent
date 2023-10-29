package org.khasanof;

import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 10/29/2023 11:41 PM
 */
public class InlineKeyboardUtilTest {

    private final InlineKeyboardUtil inlineKeyboardUtil = new SimpleInlineKeyboardUtil();

    @Test
    void createWithKeyboardButtons() {
        KeyboardButton keyboardButton1 = new KeyboardButton()
                .withText("Test")
                .withCallbackData("test");

        KeyboardButton keyboardButton2 = new KeyboardButton()
                .withText("Test")
                .withCallbackData("test");

        InlineKeyboardMarkup keyboardMarkup = inlineKeyboardUtil.createWithKeyboardButtons(
                List.of(keyboardButton1, keyboardButton2)
        );

        System.out.println("keyboardMarkup = " + keyboardMarkup);

        assertAll(() -> {
            assertEquals(1, keyboardMarkup.getKeyboard().size());
            assertEquals(2, keyboardMarkup.getKeyboard().get(0).size());
        });
    }

    @Test
    void createWithLisMap() {

        InlineKeyboardMarkup keyboardMarkup = inlineKeyboardUtil.createWithListMap(
                List.of(
                        Map.of("Test1", "Test1"),
                        Map.of("Test2", "Test2")
                )
        );

        System.out.println("keyboardMarkup = " + keyboardMarkup);

        assertAll(() -> {
            assertEquals(2, keyboardMarkup.getKeyboard().size());
            assertEquals(1, keyboardMarkup.getKeyboard().get(0).size());
            assertEquals(1, keyboardMarkup.getKeyboard().get(1).size());
        });
    }

}
