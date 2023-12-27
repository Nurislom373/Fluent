package org.khasanof.chain;

/**
 * @author Nurislom
 * @see org.khasanof.chain
 * @since 12/27/2023 9:51 PM
 */
public interface GenericChainProcessor<T> {

    void setNext(GenericChainProcessor<T> nextProcessor);

    void process(T t);

}
