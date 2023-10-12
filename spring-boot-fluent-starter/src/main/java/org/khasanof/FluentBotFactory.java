package org.khasanof;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public abstract class FluentBotFactory extends TelegramLongPollingBot {

    protected final MainHandler handler;
    protected final ApplicationProperties.Bot bot;

}
