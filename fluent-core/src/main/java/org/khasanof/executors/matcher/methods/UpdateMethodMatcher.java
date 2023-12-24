package org.khasanof.executors.matcher.methods;

import org.khasanof.enums.HandleType;
import org.khasanof.models.executors.AppropriateMethod;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher.methods
 * @since 12/24/2023 7:53 PM
 */
public abstract class UpdateMethodMatcher<P> {

    public abstract boolean isMatch(P p);

    public abstract AppropriateMethod getAppropriate(P p);

    public abstract HandleType handleType();

}
