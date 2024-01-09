package org.khasanof;

import lombok.NoArgsConstructor;
import org.khasanof.config.FluentProperties;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

/**
 * Author: Nurislom
 * <br/>
 * Date: 18.06.2023
 * <br/>
 * Time: 10:31
 * <br/>
 * Package: org.khasanof.main
 */
@NoArgsConstructor
public abstract class AbstractFluentBot extends TelegramLongPollingBot {

    protected MainHandler handler;
    protected FluentProperties.Bot bot;

    public AbstractFluentBot(MainHandler handler, FluentProperties.Bot bot) {
        super(bot.getToken());
        this.handler = handler;
        this.bot = bot;
    }
}
