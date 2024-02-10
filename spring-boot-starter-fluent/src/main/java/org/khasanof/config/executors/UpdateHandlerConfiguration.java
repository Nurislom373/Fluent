package org.khasanof.config.executors;

import org.khasanof.executors.DefaultUpdateHandler;
import org.khasanof.executors.UpdateHandler;
import org.khasanof.service.processor.UpdateChainProcessorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config.executors
 * @since 2/10/2024 8:52 PM
 */
@Configuration
public class UpdateHandlerConfiguration {

    /**
     *
     * @param service
     * @return
     */
    @Bean
    public UpdateHandler updateHandler(UpdateChainProcessorService service) {
        return new DefaultUpdateHandler(service);
    }
}
