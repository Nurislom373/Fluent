package org.khasanof;

import org.khasanof.factories.DefaultUpdateAbstractFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/9/2024 9:33 PM
 */
@Configuration
public class UpdateCreateFacadeConfiguration {

    @Bean
    public UpdateFactoryFacade updateCreatorFacade() {
        return new UpdateFactoryFacade(new DefaultUpdateAbstractFactory());
    }

}
