package org.khasanof.chain;

/**
 * @author Nurislom
 * @see org.khasanof.chain
 * @since 1/17/2024 12:45 AM
 */
public abstract class AbstractChainProcessor<T, R> implements GenericChainProcessor<T, R> {

    protected GenericChainProcessor<T, R> nextProcessor;

    public AbstractChainProcessor() {
    }

    public AbstractChainProcessor(GenericChainProcessor<T, R> nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public void setNextProcessor(GenericChainProcessor<T, R> nextProcessor) {
        this.nextProcessor = nextProcessor;
    }
}
