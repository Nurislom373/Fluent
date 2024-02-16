package org.khasanof.feature;

/**
 * @author Nurislom
 * @see org.khasanof.feature
 * @since 2/16/2024 12:04 AM
 */
public interface GenericInterceptor<T> {

    /**
     *
     * @param t
     * @return
     */
    boolean preHandle(T t);

    /**
     *
     * @param t
     */
    void postHandle(T t);
}
