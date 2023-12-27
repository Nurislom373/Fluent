package org.khasanof;

import org.khasanof.config.FluentProperties;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author Nurislom
 * @see org.khasanof.state
 * @since 10/12/2023 5:13 PM
 */
@EnableConfigurationProperties(value = {FluentProperties.class})
@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) }, basePackages = {"org.khasanof"})
public class FluentStateAutoConfiguration {
}
