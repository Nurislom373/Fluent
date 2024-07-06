package org.khasanof;

import lombok.NoArgsConstructor;
import org.khasanof.config.FluentProperties;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Author: Nurislom
 * <br/>
 * Date: 18.06.2023
 * <br/>
 * Time: 10:30
 * <br/>
 * Package: org.khasanof.main
 */
@NoArgsConstructor
public class FluentLongPollingBot extends AbstractFluentBot {

    public FluentLongPollingBot(UpdateHandlerManager handler, FluentProperties properties) {
        super(handler, properties.getBot());
    }

    @Override
    public String getBotUsername() {
        return bot.getUsername();
    }

    @Override
    public String getBotToken() {
        return bot.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        handler.process(update);
    }
}
