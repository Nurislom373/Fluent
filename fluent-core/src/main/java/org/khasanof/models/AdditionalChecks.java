package org.khasanof.models;

/**
 * @author Nurislom
 * @see org.khasanof.models
 * @since 8/13/2023 6:47 PM
 */
public interface AdditionalChecks<T> extends AdditionalType {

    boolean check(T var);

}
