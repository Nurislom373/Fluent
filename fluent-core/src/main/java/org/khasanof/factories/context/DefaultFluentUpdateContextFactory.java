package org.khasanof.factories.context;

import org.khasanof.context.FluentUpdateContext;
import org.khasanof.context.SimpleFluentUpdateContext;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.context
 * @since 12/9/2023 7:00 PM
 */
public abstract class DefaultFluentUpdateContextFactory {

    private static FluentUpdateContext INSTANCE;

    public static FluentUpdateContext create() {
        if (Objects.isNull(INSTANCE)) {
            synchronized (DefaultFluentUpdateContextFactory.class) {
                if (Objects.isNull(INSTANCE)) {
                    INSTANCE = new SimpleFluentUpdateContext();
                }
            }
        }
        return INSTANCE;
    }

}
