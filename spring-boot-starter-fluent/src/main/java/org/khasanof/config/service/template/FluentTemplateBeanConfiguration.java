package org.khasanof.config.service.template;

import org.khasanof.FluentBotSingletonBean;
import org.khasanof.service.template.DefaultFluentTemplate;
import org.khasanof.service.template.FluentTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config.service.template
 * @since 1/24/2024 9:35 PM
 */
@Configuration
public class FluentTemplateBeanConfiguration {

    /**
     *
     * @param fluentBotSingletonBean
     * @return {@link FluentTemplate} instance
     */
    @Bean(FluentTemplate.NAME)
    @ConditionalOnBean({FluentBotSingletonBean.class})
    public FluentTemplate fluentTemplate(FluentBotSingletonBean fluentBotSingletonBean) {
        return new DefaultFluentTemplate(fluentBotSingletonBean);
    }

}
