package org.khasanof.config.manager;

import org.khasanof.manager.TelegramBotApiFactoryManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config.manager
 * @since 5/13/2024 10:25 PM
 */
@Configuration
public class TelegramBotApiFactoryManagerConfig {

    /**
     *
     * @return
     */
    @Bean
    public TelegramBotApiFactoryManager botApiFactoryManager() {
        return new TelegramBotApiFactoryManager();
    }
}
