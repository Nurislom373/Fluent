package org.khasanof.factories;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.factories
 * @since 12/26/2023 9:59 PM
 */
public abstract class AbstractCachedGenericFactory<R> implements CachedNopGenericFactory<R> {

    private R instance;
    private boolean isSetReference;

    @Override
    public R cachedCreate() {
        if (Objects.nonNull(instance) && isSetReference) {
            createInstance();
        }
        return instance;
    }

    private void createInstance() {
        instance = create();
        isSetReference = true;
    }

}
