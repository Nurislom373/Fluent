package org.khasanof.models.condition;

/**
 * @author Nurislom
 * @see org.khasanof.models
 * @since 8/19/2023 6:32 PM
 */
public interface GenericCondition<P> {

    boolean match(P var);

}
