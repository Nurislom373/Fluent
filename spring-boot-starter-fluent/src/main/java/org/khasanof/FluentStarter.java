package org.khasanof;

import org.khasanof.config.FluentProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
@EnableConfigurationProperties(value = {FluentProperties.class})
@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) }, basePackages = {"org.khasanof"})
public class FluentStarter {

    @Bean
    CommandLineRunner getRunner(UpdateHandlerManager handler, FluentProperties properties, FluentBotSingletonBean singletonBean) {
        return (args -> {
            var registry = new TelegramBotsApi(DefaultBotSession.class);
            FluentBot fluentBot = new FluentBot(handler, properties);
            singletonBean.setInstance(fluentBot);
            registry.registerBot(fluentBot);
        });
    }

}
