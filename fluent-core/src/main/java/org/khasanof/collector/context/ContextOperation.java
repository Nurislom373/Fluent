package org.khasanof.collector.context;

/**
 * @author Nurislom
 * @see org.khasanof.collector.context
 * @since 1/15/2024 10:07 PM
 */
public interface ContextOperation<P, R> {

    R execute(P p);

}
