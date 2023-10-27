package org.khasanof.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 10/27/2023 9:52 PM
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "fluent.postgresql")
public class FluentPostgresqlConfig {

    /**
     * Driver Class of the database.
     */
    private String driverClassName;

    /**
     * JDBC URL of the database.
     */
    private String url;

    /**
     * Login username of the database.
     */
    private String username;

    /**
     * Login password of the database.
     */
    private String password;

}
