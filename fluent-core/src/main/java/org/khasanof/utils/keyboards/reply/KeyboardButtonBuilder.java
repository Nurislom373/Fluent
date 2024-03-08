package org.khasanof.utils.keyboards.reply;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButtonPollType;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButtonRequestChat;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButtonRequestUser;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;

/**
 * @author Nurislom
 * @see org.khasanof.utils.keyboards.reply
 * @since 3/3/2024 12:01 AM
 */
public class KeyboardButtonBuilder {

    private final KeyboardButton button;

    public KeyboardButtonBuilder() {
        this.button = new KeyboardButton();
    }

    public KeyboardButtonBuilder(String text) {
        this.button = new KeyboardButton(text);
    }

    public KeyboardButtonBuilder(KeyboardButton button) {
        this.button = button;
    }

    public KeyboardButtonBuilder text(String text) {
        this.button.setText(text);
        return this;
    }

    public KeyboardButtonBuilder requestContact(Boolean requestContact) {
        this.button.setRequestContact(requestContact);
        return this;
    }

    public KeyboardButtonBuilder requestLocation(Boolean requestLocation) {
        this.button.setRequestLocation(requestLocation);
        return this;
    }

    public KeyboardButtonBuilder requestPoll(KeyboardButtonPollType requestPoll) {
        this.button.setRequestPoll(requestPoll);
        return this;
    }

    public KeyboardButtonBuilder webApp(WebAppInfo webApp) {
        this.button.setWebApp(webApp);
        return this;
    }

    public KeyboardButtonBuilder requestUser(KeyboardButtonRequestUser requestUser) {
        this.button.setRequestUser(requestUser);
        return this;
    }

    public KeyboardButtonBuilder requestChat(KeyboardButtonRequestChat requestChat) {
        this.button.setRequestChat(requestChat);
        return this;
    }

    public KeyboardButton build() {
        return this.button;
    }
}
