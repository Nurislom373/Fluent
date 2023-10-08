package org.khasanof.model.condition;

/**
 * @author Nurislom
 * @see org.khasanof.model
 * @since 8/19/2023 6:32 PM
 */
public interface Condition<P> {

    boolean match(P var);

}
