package org.khasanof;

import org.khasanof.config.FluentPostgresqlConfig;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 11/17/2023 11:33 PM
 */
@EnableConfigurationProperties(value = {FluentPostgresqlConfig.class})
@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) }, basePackages = {"org.khasanof"})
public class FluentStatePostgresqlAutoConfiguration {
}
