package org.khasanof;

import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import static org.khasanof.constants.FluentConstants.BASE_PACKAGE;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 3/3/2024 5:27 PM
 */
@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) }, basePackages = {BASE_PACKAGE})
public class FluentInlineQueryAutoConfiguration {
}
