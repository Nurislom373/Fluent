package org.khasanof.feature;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof.feature
 * @since 2/1/2024 10:06 PM
 */
public interface FluentInterceptor {

    default boolean preHandle(Update update) throws Exception {
        return true;
    }

}
