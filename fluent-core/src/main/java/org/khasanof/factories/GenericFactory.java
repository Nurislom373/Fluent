package org.khasanof.factories;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 12/15/2023 4:56 PM
 */
public interface GenericFactory<P, R> {

    R create(P p);

}
