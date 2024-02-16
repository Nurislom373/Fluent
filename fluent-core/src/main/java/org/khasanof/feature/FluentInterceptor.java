package org.khasanof.feature;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof.feature
 * @since 2/1/2024 10:06 PM
 */
public interface FluentInterceptor extends GenericInterceptor<Update> {

    default boolean preHandle(Update update) {
        return true;
    }

    default void postHandle(Update update) {
    }

}
