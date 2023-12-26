package org.khasanof.factories;

/**
 * @author Nurislom
 * @see org.khasanof.factories
 * @since 12/26/2023 9:56 PM
 */
public interface CachedNopGenericFactory<R> extends NopGenericFactory<R> {

    R cachedCreate();

}
