package org.khasanof;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import static org.khasanof.constants.FluentConstants.BASE_PACKAGE;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 11/17/2023 11:33 PM
 */
@Slf4j
@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) }, basePackages = {BASE_PACKAGE})
public class SpringBootFluentPostgresqlAutoConfiguration {

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> log.info("Postgresql Strategy Initialize!!!");
    }
}
