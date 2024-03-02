package org.khasanof.utils.keyboards.inline;

import org.telegram.telegrambots.meta.api.objects.LoginUrl;
import org.telegram.telegrambots.meta.api.objects.games.CallbackGame;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;

/**
 * @author Nurislom
 * @see org.khasanof.utils.keyboards
 * @since 3/2/2024 7:25 PM
 */
public class InlineKeyboardBuilder {

    private final InlineKeyboardButton button;

    public InlineKeyboardBuilder() {
        this.button = new InlineKeyboardButton();
    }

    public InlineKeyboardBuilder(String text) {
        this.button = new InlineKeyboardButton(text);
    }

    public InlineKeyboardBuilder(InlineKeyboardButton button) {
        this.button = button;
    }

    public InlineKeyboardBuilder url(String url) {
        this.button.setUrl(url);
        return this;
    }

    public InlineKeyboardBuilder text(String text) {
        this.button.setText(text);
        return this;
    }

    public InlineKeyboardBuilder callbackData(String callbackData) {
        this.button.setCallbackData(callbackData);
        return this;
    }

    public InlineKeyboardBuilder callbackGame(CallbackGame callbackGame) {
        this.button.setCallbackGame(callbackGame);
        return this;
    }

    public InlineKeyboardBuilder switchInlineQuery(String switchInlineQuery) {
        this.button.setSwitchInlineQuery(switchInlineQuery);
        return this;
    }

    public InlineKeyboardBuilder switchInlineQueryCurrentChat(String switchInlineQueryCurrentChat) {
        this.button.setSwitchInlineQueryCurrentChat(switchInlineQueryCurrentChat);
        return this;
    }

    public InlineKeyboardBuilder pay(Boolean pay) {
        this.button.setPay(pay);
        return this;
    }

    public InlineKeyboardBuilder loginUrl(LoginUrl loginUrl) {
        this.button.setLoginUrl(loginUrl);
        return this;
    }

    public InlineKeyboardBuilder webApp(WebAppInfo webAppInfo) {
        this.button.setWebApp(webAppInfo);
        return this;
    }

    public InlineKeyboardButton build() {
        return this.button;
    }
}
