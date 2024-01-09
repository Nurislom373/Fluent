package org.khasanof;

import org.khasanof.factories.UpdateAbstractFactory;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/9/2024 9:31 PM
 */
public class UpdateFactoryFacade {

    private final UpdateAbstractFactory updateAbstractFactory;

    public UpdateFactoryFacade(UpdateAbstractFactory updateAbstractFactory) {
        this.updateAbstractFactory = updateAbstractFactory;
    }

    public Update createWithText(String text) {
        return updateAbstractFactory.updateMessageFactory()
                .create(text);
    }

}
