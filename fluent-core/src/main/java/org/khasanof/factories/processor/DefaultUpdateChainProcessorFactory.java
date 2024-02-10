package org.khasanof.factories.processor;

import org.khasanof.executors.processor.AbstractUpdateChainProcessor;
import org.khasanof.registry.processor.UpdateChainProcessorRegistryContainer;

import java.util.Iterator;

/**
 * @author Nurislom
 * @see org.khasanof.factories.processor
 * @since 2/1/2024 9:54 PM
 */
public class DefaultUpdateChainProcessorFactory extends CachedUpdateChainProcessorFactory {

    private final UpdateChainProcessorRegistryContainer registryContainer;

    public DefaultUpdateChainProcessorFactory(UpdateChainProcessorRegistryContainer registryContainer) {
        this.registryContainer = registryContainer;
    }

    @Override
    public AbstractUpdateChainProcessor create() {
        Iterator<AbstractUpdateChainProcessor> iterator = getProcessorIterator();
        AbstractUpdateChainProcessor chainProcessor = iterator.next();
        setNexProcessors(iterator, chainProcessor);
        return chainProcessor;
    }

    private Iterator<AbstractUpdateChainProcessor> getProcessorIterator() {
        return registryContainer.getUpdateChainProcessors().iterator();
    }

    private void setNexProcessors(Iterator<AbstractUpdateChainProcessor> iterator, AbstractUpdateChainProcessor chainProcessor) {
        while (iterator.hasNext()) {
            AbstractUpdateChainProcessor nextChainProcessor = iterator.next();
            chainProcessor.setNext(nextChainProcessor);
            chainProcessor = nextChainProcessor;
        }
    }
}
