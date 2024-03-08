package org.khasanof.chain;

/**
 * @author Nurislom
 * @see org.khasanof.chain
 * @since 12/27/2023 9:56 PM
 */
public abstract class AbstractNopChainProcessor<T> implements GenericNopChainProcessor<T> {

    protected GenericNopChainProcessor<T> nextProcessor;

    @Override
    public void setNext(GenericNopChainProcessor<T> nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

}
