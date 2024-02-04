package org.khasanof.service.runnable.processor;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.config.executors.processor.UpdateChainProcessorAutoConfiguration;
import org.khasanof.registry.processor.UpdateChainProcessorRegistryContainer;
import org.khasanof.service.runnable.PostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author Nurislom
 * @see org.khasanof.service.runnable.processor
 * @since 2/4/2024 10:52 PM
 */
@Slf4j
@Service
public class UpdateChainProcessorRegistryPostProcessor implements PostProcessor {

    private final ApplicationContext applicationContext;
    private final UpdateChainProcessorRegistryContainer registryContainer;

    public UpdateChainProcessorRegistryPostProcessor(ApplicationContext applicationContext,
                                                     UpdateChainProcessorRegistryContainer registryContainer) {

        this.applicationContext = applicationContext;
        this.registryContainer = registryContainer;
    }

    @Override
    public void run() {
        log.info("Chain Processor Registry Started!");
        var chainProcessorBuilder = new UpdateChainProcessorAutoConfiguration.ChainProcessorBuilder(applicationContext);
        registryContainer.addUpdateChainProcessors(chainProcessorBuilder.processors());
    }
}
