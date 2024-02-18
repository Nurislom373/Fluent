package org.khasanof;

import org.khasanof.collector.context.SimpleMethodContext;
import org.khasanof.factories.DefaultUpdateAbstractFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/9/2024 9:33 PM
 */
@Configuration
public class UpdateFactoryFacadeConfiguration {

    @Bean
    public UpdateFactoryFacade updateCreatorFacade() {
        return new UpdateFactoryFacade(new DefaultUpdateAbstractFactory());
    }
}
