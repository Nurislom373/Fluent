package org.khasanof.collector;

/**
 * @author Nurislom
 * @see org.khasanof.springbootstarterfluent.core.collector
 * @since 8/19/2023 12:22 PM
 */
public interface GenericMethodContext<T, R> {

    R getMethodsByGenericKey(T key);

    boolean containsKey(T key);

}
