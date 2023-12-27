package org.khasanof;

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
public abstract class AbstractFluentBot extends TelegramLongPollingBot {

    protected final MainHandler handler;
    protected final FluentProperties.Bot bot;

    public AbstractFluentBot(MainHandler handler, FluentProperties.Bot bot) {
        super(bot.getToken());
        this.handler = handler;
        this.bot = bot;
    }
}
