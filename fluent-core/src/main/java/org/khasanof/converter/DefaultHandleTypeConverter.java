package org.khasanof.converter;

import org.khasanof.utils.HandleTypeUtils;

/**
 * @author Nurislom
 * @see org.khasanof.converter
 * @since 1/6/2024 8:55 PM
 */
public class DefaultHandleTypeConverter extends HandleTypeConverter {

    public DefaultHandleTypeConverter() {
        super(HandleTypeUtils::handleTypeToClass, HandleTypeUtils::handleClassToType);
    }

}
