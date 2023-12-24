package org.khasanof.context.singleton;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Nurislom
 * @see org.khasanof.context.singleton
 * @since 12/10/2023 12:56 PM
 */
public abstract class GenericSingleton<T> {

    private T INSTANCE;
    private boolean isSetReference;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public T getInstance() {
        readWriteLock.readLock().lock();
        try {
            return INSTANCE;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void setInstance(T INSTANCE) {
        readWriteLock.writeLock().lock();
        try {
           trySetInstance(INSTANCE);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    private void trySetInstance(T INSTANCE) {
        if (!isSetReference) {
            this.INSTANCE = INSTANCE;
            isSetReference = true;
        }
    }

}
