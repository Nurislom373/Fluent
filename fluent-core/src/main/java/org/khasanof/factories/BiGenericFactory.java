package org.khasanof.factories;

/**
 * @author Nurislom
 * @see org.khasanof.factories
 * @since 12/26/2023 1:19 AM
 */
public interface BiGenericFactory<P1, P2, R> {

    R create(P1 p1, P2 p2);

}
