package org.khasanof.factories.processor;

import org.khasanof.executors.processor.AbstractUpdateChainProcessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.khasanof.utils.SortUtils.sortList;

/**
 * @author Nurislom
 * @see org.khasanof.factories.processor
 * @since 2/1/2024 9:54 PM
 */
public class DefaultUpdateChainProcessorFactory implements UpdateChainProcessorFactory {

    private final List<AbstractUpdateChainProcessor> processors = new ArrayList<>();

    @Override
    public AbstractUpdateChainProcessor create() {
        Iterator<AbstractUpdateChainProcessor> iterator = processors.iterator();
        AbstractUpdateChainProcessor chainProcessor = iterator.next();
        setNexProcessors(iterator, chainProcessor);
        return chainProcessor;
    }

    private void setNexProcessors(Iterator<AbstractUpdateChainProcessor> iterator, AbstractUpdateChainProcessor chainProcessor) {
        while (iterator.hasNext()) {
            AbstractUpdateChainProcessor nextChainProcessor = iterator.next();
            chainProcessor.setNext(nextChainProcessor);
            chainProcessor = nextChainProcessor;
        }
    }

    @Override
    public void set(AbstractUpdateChainProcessor abstractUpdateChainProcessor) {
        processors.add(abstractUpdateChainProcessor);
        sortList(processors);
    }

    @Override
    public void setAll(Collection<AbstractUpdateChainProcessor> collection) {
        processors.addAll(collection);
        sortList(processors);
    }
}
