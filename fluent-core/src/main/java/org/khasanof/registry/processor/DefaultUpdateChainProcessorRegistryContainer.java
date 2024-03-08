package org.khasanof.registry.processor;

import org.khasanof.executors.processor.AbstractUpdateChainProcessor;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.khasanof.utils.SortUtils.sortList;

/**
 * @author Nurislom
 * @see org.khasanof.registry.processor
 * @since 2/4/2024 10:47 PM
 */
public class DefaultUpdateChainProcessorRegistryContainer implements UpdateChainProcessorRegistryContainer {

    private final List<AbstractUpdateChainProcessor> processors = new CopyOnWriteArrayList<>();

    @Override
    public List<AbstractUpdateChainProcessor> getUpdateChainProcessors() {
        return this.processors;
    }

    @Override
    public void addUpdateChainProcessors(List<AbstractUpdateChainProcessor> processors) {
        if (Objects.nonNull(processors)) {
            this.processors.addAll(processors);
        }
        sortProcessors();
    }

    @Override
    public void addUpdateChainProcessor(AbstractUpdateChainProcessor processor) {
        if (Objects.nonNull(processor)) {
            this.processors.add(processor);
        }
        sortProcessors();
    }

    private void sortProcessors() {
        sortList(this.processors);
    }
}
