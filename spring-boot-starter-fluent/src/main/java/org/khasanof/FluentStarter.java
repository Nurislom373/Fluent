package org.khasanof;

import org.khasanof.config.ApplicationProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * Author: Nurislom
 * <br/>
 * Date: 18.06.2023
 * <br/>
 * Time: 11:21
 * <br/>
 * Package: org.khasanof.main
 */
@EnableConfigurationProperties(value = {ApplicationProperties.class})
@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) }, basePackages = {"org.khasanof"})
public class FluentStarter {

    @Bean
    CommandLineRunner getRunner(ApplicationContext context) {
        return (args -> {
            var registry = new TelegramBotsApi(DefaultBotSession.class);
            var bot = context.getBean(FluentBot.class);
            registry.registerBot(bot);
        });
    }

}
