package org.khasanof.state;

/**
 * @author Nurislom
 * @see org.khasanof.state
 * @since 8/15/2023 9:18 PM
 */
public interface StateAction<S extends Enum> extends Action, DefaultNext<S> {

    S state();

    @Override
    default boolean updateHandlersProceed() {
        return false;
    }

}
