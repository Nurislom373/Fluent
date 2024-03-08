package org.khasanof.context;

import lombok.Getter;
import org.khasanof.custom.attributes.Attributes;
import org.khasanof.factories.context.DefaultFluentUpdateContextFactory;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof.context
 * @since 10/28/2023 7:23 PM
 */
public abstract class FluentContextHolder {

    @Getter
    private static final FluentUpdateContext context = DefaultFluentUpdateContextFactory.create();

    /**
     *
     * @return current {@link Update}
     */
    public static Update getCurrentUpdate() {
        return getCurrentFluentUpdate().getUpdate();
    }

    /**
     *
     * @return current {@link Attributes}
     */
    public static Attributes getCurrentAttributes() {
        return getCurrentFluentUpdate().getAttributes();
    }

    /**
     *
     * @return current {@link FluentUpdate}
     */
    public static FluentUpdate getCurrentFluentUpdate() {
        return context.getFluentUpdate(getThreadName())
                .orElseThrow(() -> new RuntimeException("UpdateContext not found!"));
    }

    private static String getThreadName() {
        return Thread.currentThread().getName();
    }
}
