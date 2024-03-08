package org.khasanof.registry.processor;

import org.khasanof.executors.processor.AbstractUpdateChainProcessor;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.registry.processor
 * @since 2/4/2024 10:45 PM
 */
public interface UpdateChainProcessorRegistryContainer {

    /**
     *
     * @return
     */
    List<AbstractUpdateChainProcessor> getUpdateChainProcessors();

    /**
     *
     * @param processor
     */
    void addUpdateChainProcessors(List<AbstractUpdateChainProcessor> processor);

    /**
     *
     * @param processor
     */
    void addUpdateChainProcessor(AbstractUpdateChainProcessor processor);
}
