package org.khasanof.context;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.context
 * @since 12/9/2023 7:00 PM
 */
public abstract class FluentUpdateContextFactory {

    private static FluentUpdateContext INSTANCE;

    public static FluentUpdateContext create() {
        if (Objects.isNull(INSTANCE)) {
            synchronized (FluentUpdateContextFactory.class) {
                if (Objects.isNull(INSTANCE)) {
                    INSTANCE = new SimpleFluentUpdateContext();
                }
            }
        }
        return INSTANCE;
    }

}
