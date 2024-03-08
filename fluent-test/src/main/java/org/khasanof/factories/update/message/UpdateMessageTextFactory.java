package org.khasanof.factories.update.message;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof.factories.update
 * @since 1/9/2024 9:21 PM
 */
public interface UpdateMessageTextFactory {

    Update create(String text);

}
