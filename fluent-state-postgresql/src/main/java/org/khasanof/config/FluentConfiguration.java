package org.khasanof.config;

import org.khasanof.DataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * @author Nurislom
 * @see org.khasanof.config
 * @since 11/17/2023 11:30 PM
 */
@Configuration
public class FluentConfiguration {

    @Bean
    @Primary
    public NamedParameterJdbcTemplate parameterJdbcTemplate(DataSourceFactory dataSourceFactory) {
        return new NamedParameterJdbcTemplate(dataSourceFactory.createDataSource());
    }

}
