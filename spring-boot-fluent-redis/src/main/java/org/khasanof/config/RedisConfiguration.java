package org.khasanof.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Nurislom
 * @see org.khasanof.config
 * @since 3/16/2024 7:57 PM
 */
@Configuration
public class RedisConfiguration {

    @Autowired
    private RedisProperties redisProperties;

    /**
     *
     * @return
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory(configuration());
    }

    private RedisStandaloneConfiguration configuration() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();

        configuration.setPort(redisProperties.getPort());
        configuration.setHostName(redisProperties.getHostName());
        configuration.setDatabase(redisProperties.getDatabase());
        configuration.setUsername(redisProperties.getUsername());
        configuration.setPassword(redisProperties.getPassword());

        return configuration;
    }

    /**
     *
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}
