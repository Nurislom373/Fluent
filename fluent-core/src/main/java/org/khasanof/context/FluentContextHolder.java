package org.khasanof.context;

import org.khasanof.custom.attributes.UpdateAttributes;

/**
 * @author Nurislom
 * @see org.khasanof.context
 * @since 10/28/2023 7:23 PM
 */
public abstract class FluentContextHolder {

    private static final FluentUpdateContext context = FluentUpdateContextFactory.create();

    public static FluentUpdate getCurrentUpdate() {
        return context.getFluentUpdate(getThreadName())
                .orElseThrow(() -> new RuntimeException("UpdateContext not found!"));
    }

    public static UpdateAttributes getAttributes() {
        return getCurrentUpdate().getAttributes();
    }

    private static String getThreadName() {
        return Thread.currentThread().getName();
    }

    public static FluentUpdateContext getContext() {
        return context;
    }

}
