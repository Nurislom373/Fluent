package org.khasanof;

import org.khasanof.config.FluentProperties;
import org.khasanof.service.bot.TelegramBotRegistryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import static org.khasanof.constants.FluentConstants.BASE_PACKAGE;

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
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) }, basePackages = {BASE_PACKAGE})
public class FluentStarterAutoConfiguration {

    /**
     *
     * @param botRegistryService
     * @return
     */
    @Bean
    CommandLineRunner getRunner(TelegramBotRegistryService botRegistryService) {
        return args -> botRegistryService.registry();
    }
}
