package org.khasanof.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * @author Nurislom
 * @see org.khasanof.config
 * @since 11/17/2023 11:30 PM
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "org.khasanof.repository",
        entityManagerFactoryRef = "fluentEntityManagerFactory",
        transactionManagerRef = "fluentTransactionManager"
)
public class FluentPostgresqlConfiguration {

    @Autowired
    private Environment env;

    /**
     *
     * @return {@link LocalContainerEntityManagerFactoryBean} bean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean fluentEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(fluentDataSource());
        em.setPackagesToScan("org.khasanof.entity");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        fillProperties(properties);
        em.setJpaPropertyMap(properties);
        return em;
    }

    private void fillProperties(HashMap<String, Object> properties) {
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("fluent.datasource.properties.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("fluent.datasource.properties.hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("fluent.datasource.properties.hibernate.show-sql"));
        properties.put("hibernate.id.new_generator_mappings", env.getProperty("fluent.datasource.properties.hibernate.id.new_generator_mappings"));
        properties.put("hibernate.connection.provider_disables_autocommit", env.getProperty("fluent.datasource.properties.hibernate.connection.provider_disables_autocommit"));
        properties.put("hibernate.cache.use_second_level_cache", env.getProperty("fluent.datasource.properties.hibernate.cache.use_second_level_cache"));
        properties.put("hibernate.cache.use_query_cache", env.getProperty("fluent.datasource.properties.hibernate.cache.use_query_cache"));
        properties.put("hibernate.generate_statistics", env.getProperty("fluent.datasource.properties.hibernate.generate_statistics"));
        properties.put("hibernate.jdbc.time_zone", env.getProperty("fluent.datasource.properties.hibernate.jdbc.time_zone"));
        properties.put("hibernate.jdbc.fetch_size", env.getProperty("fluent.datasource.properties.hibernate.jdbc.fetch_size"));
        properties.put("hibernate.jdbc.batch_size", env.getProperty("fluent.datasource.properties.hibernate.jdbc.batch_size"));
        properties.put("hibernate.connection.release_mode", env.getProperty("fluent.datasource.properties.hibernate.connection.release_mode"));
    }

    /**
     *
     * @return {@link DataSource} bean
     */
    @Bean
    @ConfigurationProperties(prefix = "fluent.datasource")
    public DataSource fluentDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     *
     * @return {@link PlatformTransactionManager} bean
     */
    @Bean
    public PlatformTransactionManager fluentTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(fluentEntityManagerFactory().getObject());
        return jpaTransactionManager;
    }
}
