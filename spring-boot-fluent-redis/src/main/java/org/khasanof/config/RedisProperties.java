package org.khasanof.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.connection.RedisPassword;

/**
 * @author Nurislom
 * @see org.khasanof.config
 * @since 3/17/2024 12:34 AM
 */
@Data
@ConfigurationProperties(prefix = "fluent.redis")
public class RedisProperties {

    private String hostName = "localhost";
    private Integer port = 6379;
    private int database;
    private String username;
    private RedisPassword password = RedisPassword.none();
}
