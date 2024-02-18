package org.khasanof.executors.appropriate;

import org.khasanof.models.executors.AppropriateMethod;
import org.khasanof.models.executors.UpdateType;

/**
 * @author Nurislom
 * @see org.khasanof.executors.appropriate
 * @since 12/24/2023 7:53 PM
 */
public abstract class AppropriateUpdateMethod<P> {

    /**
     *
     * @param p
     * @return
     */
    public abstract boolean isMatch(P p);

    /**
     *
     * @param p
     * @return
     */
    public abstract AppropriateMethod getAppropriate(P p);

    /**
     *
     * @return
     */
    public abstract UpdateType handleType();
}
