package org.khasanof.model;

/**
 * @author Nurislom
 * @see org.khasanof.model
 * @since 8/13/2023 6:47 PM
 */
public interface AdditionalChecks<T> extends AdditionalType {

    boolean check(T var);

}
