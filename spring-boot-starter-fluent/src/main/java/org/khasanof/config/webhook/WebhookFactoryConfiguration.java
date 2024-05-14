package org.khasanof.config.webhook;

import org.khasanof.factories.webhook.DefaultSetWebhookFactory;
import org.khasanof.factories.webhook.DefaultWebhookFactory;
import org.khasanof.factories.webhook.SetWebhookFactory;
import org.khasanof.factories.webhook.WebhookFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config.webhook
 * @since 5/13/2024 10:25 PM
 */
@Configuration
public class WebhookFactoryConfiguration {

    /**
     *
     * @return
     */
    @Bean
    public WebhookFactory webhookFactory() {
        return new DefaultWebhookFactory();
    }

    /**
     *
     * @return
     */
    @Bean
    public SetWebhookFactory setWebhookFactory() {
        return new DefaultSetWebhookFactory();
    }
}
