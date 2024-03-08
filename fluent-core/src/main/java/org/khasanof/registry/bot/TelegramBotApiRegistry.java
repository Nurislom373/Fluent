package org.khasanof.registry.bot;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.khasanof.factories.bot.TelegramBotApiFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

/**
 * @author Nurislom
 * @see org.khasanof.registry.bot
 * @since 3/6/2024 10:43 PM
 */
@Slf4j
@Getter
public class TelegramBotApiRegistry {

    private static TelegramBotApiRegistry registry;
    private final TelegramBotsApi telegramBotsApi;
    private Boolean alreadyRegistryBot;

    private TelegramBotApiRegistry(TelegramBotsApi telegramBotsApi) {
        this.telegramBotsApi = telegramBotsApi;
        this.alreadyRegistryBot = false;
    }

    /**
     *
     * @param factory
     */
    public static void createInstance(TelegramBotApiFactory factory) {
        initialize(factory);
    }

    /**
     *
     * @return
     */
    public static TelegramBotApiRegistry getBotApiRegistryInstance() {
        return registry;
    }

    /**
     *
      * @param bot
     * @throws TelegramApiException
     */
    public void registerBot(LongPollingBot bot) throws TelegramApiException {
        if (!alreadyRegistryBot) {
            this.telegramBotsApi.registerBot(bot);
            this.alreadyRegistryBot = true;
            return;
        }
        log.warn("fluent bot already registered!");
    }

    private static void initialize(TelegramBotApiFactory factory) {
        registry = new TelegramBotApiRegistry(factory.create());
    }
}
