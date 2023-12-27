package org.khasanof.chain;

/**
 * @author Nurislom
 * @see org.khasanof.chain
 * @since 12/27/2023 9:56 PM
 */
public abstract class AbstractChainProcessor<T> implements GenericChainProcessor<T> {

    protected GenericChainProcessor<T> nextProcessor;

    @Override
    public void setNext(GenericChainProcessor<T> nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

}
