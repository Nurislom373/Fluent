package org.khasanof.executors.appropriate;

import org.khasanof.enums.HandleType;
import org.khasanof.models.executors.AppropriateMethod;

/**
 * @author Nurislom
 * @see org.khasanof.executors.appropriate
 * @since 12/24/2023 7:53 PM
 */
public abstract class AppropriateUpdateMethod<P> {

    public abstract boolean isMatch(P p);

    public abstract AppropriateMethod getAppropriate(P p);

    public abstract HandleType handleType();

}
