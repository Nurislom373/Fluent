package org.khasanof.context;

import org.khasanof.custom.attributes.Attributes;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof.context
 * @since 10/28/2023 6:53 PM
 */
public interface FluentUpdate {

    Update getUpdate();

    Attributes getAttributes();

}
