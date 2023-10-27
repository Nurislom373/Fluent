package org.khasanof;

import org.khasanof.config.FluentPostgresqlConfig;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 10/27/2023 10:12 PM
 */
@Component
public class SimpleDataSourceFactory implements DataSourceFactory {

    private final FluentPostgresqlConfig config;

    public SimpleDataSourceFactory(FluentPostgresqlConfig config) {
        this.config = config;
    }

    @Override
    public DataSource createDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(config.getDriverClassName());
        dataSourceBuilder.url(config.getUrl());
        dataSourceBuilder.username(config.getUsername());
        dataSourceBuilder.password(config.getPassword());
        return dataSourceBuilder.build();
    }

}
