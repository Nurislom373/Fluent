package org.khasanof;

import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 12/7/2023 12:18 AM
 */
public interface GenericFind<P, R> {

    Optional<R> find(P p);

}
