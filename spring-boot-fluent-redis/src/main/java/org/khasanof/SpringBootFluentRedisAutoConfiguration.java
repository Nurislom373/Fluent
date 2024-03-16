package org.khasanof;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.config.RedisProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Repository;

import static org.khasanof.constants.FluentConstants.BASE_PACKAGE;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 3/17/2024 12:38 AM
 */
@Slf4j
@EnableConfigurationProperties({RedisProperties.class})
@EnableRedisRepositories(basePackages = {BASE_PACKAGE},
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) }, basePackages = {BASE_PACKAGE})
public class SpringBootFluentRedisAutoConfiguration {

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> log.info("Redis Strategy Initialize!!!");
    }
}
