package org.khasanof.chain;

/**
 * @author Nurislom
 * @see org.khasanof.chain
 * @since 1/17/2024 12:44 AM
 */
public interface GenericChainProcessor<T, R> {

    void setNextProcessor(GenericChainProcessor<T, R> nextProcessor);

    R process(T t);

}
