package org.khasanof.factories;

import org.khasanof.factories.update.DefaultUpdateMessageFactory;
import org.khasanof.factories.update.UpdateMessageFactory;

/**
 * @author Nurislom
 * @see org.khasanof.factories
 * @since 1/9/2024 9:29 PM
 */
public class DefaultUpdateAbstractFactory implements UpdateAbstractFactory {

    @Override
    public UpdateMessageFactory updateMessageFactory() {
        return new DefaultUpdateMessageFactory();
    }

}
