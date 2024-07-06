package org.khasanof.service.bot;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.FluentBotSingletonBean;
import org.khasanof.FluentLongPollingBot;
import org.khasanof.FluentWebhookBot;
import org.khasanof.UpdateHandlerManager;
import org.khasanof.config.FluentProperties;
import org.khasanof.enums.BotType;
import org.khasanof.factories.bot.TelegramBotApiFactory;
import org.khasanof.factories.webhook.SetWebhookFactory;
import org.khasanof.factories.webhook.WebhookFactory;
import org.khasanof.manager.TelegramBotApiFactoryManager;
import org.khasanof.registry.bot.TelegramBotApiRegistry;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Objects;

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

    private final TelegramBotApiFactoryManager botApiFactoryManager;
    private final FluentBotSingletonBean singletonBean;
    private final SetWebhookFactory setWebhookFactory;
    private final WebhookFactory webhookFactory;
    private final UpdateHandlerManager handler;
    private final FluentProperties properties;

    public DefaultTelegramBotRegistryService(TelegramBotApiFactoryManager botApiFactoryManager,
                                             FluentBotSingletonBean singletonBean,
                                             SetWebhookFactory setWebhookFactory,
                                             WebhookFactory webhookFactory,
                                             UpdateHandlerManager handler,
                                             FluentProperties properties) {

        this.botApiFactoryManager = botApiFactoryManager;
        this.setWebhookFactory = setWebhookFactory;
        this.webhookFactory = webhookFactory;
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
        TelegramBotApiFactory telegramBotApiFactory = botApiFactoryManager.getFactory(properties, webhookFactory);
        createInstance(telegramBotApiFactory);

        TelegramBotApiRegistry botApiRegistryInstance = getBotApiRegistryInstance();
        internalRegistry(botApiRegistryInstance);
    }

    private void internalRegistry(TelegramBotApiRegistry botApiRegistryInstance) throws TelegramApiException {
        if (Objects.equals(properties.getBot().getType(), BotType.LONG_POLLING)) {
            botApiRegistryInstance.registerBot(createLongPollingBotInstance());
            return;
        }
        SetWebhook setWebhook = setWebhookFactory.create(properties.getWebhook());
        botApiRegistryInstance.registerBot(createWebhookInstance(setWebhook), setWebhook);
    }

    private FluentWebhookBot createWebhookInstance(SetWebhook setWebhook) {
        return new FluentWebhookBot(handler, properties, setWebhook);
    }

    private FluentLongPollingBot createLongPollingBotInstance() {
        FluentLongPollingBot fluentLongPollingBot = new FluentLongPollingBot(handler, properties);
        singletonBean.setInstance(fluentLongPollingBot);
        return fluentLongPollingBot;
    }
}
