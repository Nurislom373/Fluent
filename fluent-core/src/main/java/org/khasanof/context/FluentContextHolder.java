package org.khasanof.context;

import lombok.Getter;
import org.khasanof.custom.attributes.UpdateAttributes;
import org.khasanof.factories.context.DefaultFluentUpdateContextFactory;

/**
 * @author Nurislom
 * @see org.khasanof.context
 * @since 10/28/2023 7:23 PM
 */
public abstract class FluentContextHolder {

    @Getter
    private static final FluentUpdateContext context = DefaultFluentUpdateContextFactory.create();

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

}
