package org.khasanof.service.bot;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.FluentBot;
import org.khasanof.FluentBotSingletonBean;
import org.khasanof.UpdateHandlerManager;
import org.khasanof.config.FluentProperties;
import org.khasanof.factories.bot.DefaultTelegramBotApiFactory;
import org.khasanof.factories.bot.TelegramBotApiFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import static org.khasanof.registry.bot.TelegramBotApiRegistry.createInstance;
import static org.khasanof.registry.bot.TelegramBotApiRegistry.getBotApiRegistryInstance;

/**
 * @author Nurislom
 * @see org.khasanof.service.bot
 * @since 3/7/2024 10:08 PM
 */
@Slf4j
@Service
public class DefaultTelegramBotRegistryService implements TelegramBotRegistryService {

    private final TelegramBotApiFactory factory = new DefaultTelegramBotApiFactory(DefaultBotSession.class);
    private final FluentBotSingletonBean singletonBean;
    private final UpdateHandlerManager handler;
    private final FluentProperties properties;

    public DefaultTelegramBotRegistryService(FluentBotSingletonBean singletonBean,
                                             UpdateHandlerManager handler,
                                             FluentProperties properties) {

        this.singletonBean = singletonBean;
        this.handler = handler;
        this.properties = properties;
    }

    @Override
    public void registry() {
        try {
            tryRegistry();
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void tryRegistry() throws TelegramApiException {
        createInstance(factory);
        getBotApiRegistryInstance().registerBot(createBotInstance());
    }

    private FluentBot createBotInstance() {
        FluentBot fluentBot = new FluentBot(handler, properties);
        singletonBean.setInstance(fluentBot);
        return fluentBot;
    }
}
