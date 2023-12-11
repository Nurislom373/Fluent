package org.khasanof;

import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 12/7/2023 12:18 AM
 */
public interface GenericFind<P, R> {

    R findOne(P p);

    Set<R> findAll(P p);

}
