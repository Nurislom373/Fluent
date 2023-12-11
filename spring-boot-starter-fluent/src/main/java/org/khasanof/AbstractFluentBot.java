package org.khasanof;

import org.khasanof.config.ApplicationProperties;
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
    protected final ApplicationProperties.Bot bot;

    public AbstractFluentBot(MainHandler handler, ApplicationProperties.Bot bot) {
        super(bot.getToken());
        this.handler = handler;
        this.bot = bot;
    }
}
