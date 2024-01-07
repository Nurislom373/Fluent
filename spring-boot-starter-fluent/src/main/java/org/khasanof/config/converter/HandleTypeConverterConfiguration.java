package org.khasanof.config.converter;

import org.khasanof.converter.DefaultHandleTypeConverter;
import org.khasanof.converter.HandleTypeConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config.converter
 * @since 1/6/2024 9:16 PM
 */
@Configuration
public class HandleTypeConverterConfiguration {

    @Bean
    public HandleTypeConverter handleTypeConverter() {
        return new DefaultHandleTypeConverter();
    }

}
