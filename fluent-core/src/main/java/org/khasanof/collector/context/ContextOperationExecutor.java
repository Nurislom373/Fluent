package org.khasanof.collector.context;

/**
 * @author Nurislom
 * @see org.khasanof.collector.context
 * @since 1/15/2024 10:16 PM
 */
public interface ContextOperationExecutor {

    <P, R> R execute(Class<? extends ContextOperation<P, R>> operationClass, P data);

}
