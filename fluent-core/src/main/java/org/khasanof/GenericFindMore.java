package org.khasanof;

import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 12/14/2023 1:06 AM
 */
public interface GenericFindMore<P, R> {

    Set<R> findMore(P p);

}
