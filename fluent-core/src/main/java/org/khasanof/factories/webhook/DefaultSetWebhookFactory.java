package org.khasanof.factories.webhook;

import org.khasanof.config.FluentProperties;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.factories.webhook
 * @since 5/13/2024 10:07 PM
 */
public class DefaultSetWebhookFactory implements SetWebhookFactory {

    @Override
    public SetWebhook create(FluentProperties.Webhook webhook) {
        SetWebhook setWebhook = new SetWebhook(webhook.getUrl());
        internalCreate(webhook, setWebhook);
        return setWebhook;
    }

    private void internalCreate(FluentProperties.Webhook webhook, SetWebhook setWebhook) {
        try {
            tryInternalCreate(webhook, setWebhook);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void tryInternalCreate(FluentProperties.Webhook webhook, SetWebhook setWebhook) throws FileNotFoundException {
        setWebhook.setUrl(webhook.getUrl());
        setWebhook.setSecretToken(webhook.getSecretToken());
        setWebhook.setAllowedUpdates(webhook.getAllowedUpdates());
        setWebhook.setMaxConnections(webhook.getMaxConnections());
        setWebhook.setDropPendingUpdates(webhook.getDropPendingUpdates());

        if (Objects.nonNull(webhook.getKeystoreServerFile())) {
            setWebhook.setCertificate(loadCertificate(webhook));
        }
    }

    private InputFile loadCertificate(FluentProperties.Webhook webhook) throws FileNotFoundException {
        return new InputFile(new FileInputStream(webhook.getKeystoreServerFile()), webhook.getKeystoreServerFile());
    }
}
