package org.khasanof;

import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 8/18/2023 5:46 AM
 */
public interface ObjectCollector<T> {

    Set<T> getAll();

    void addAll(Set<T> ts);

}
