package org.khasanof.converter;

import org.khasanof.enums.HandleClasses;
import org.khasanof.enums.HandleType;

import java.util.function.Function;

/**
 * @author Nurislom
 * @see org.khasanof.converter
 * @since 1/6/2024 8:44 PM
 */
public abstract class HandleTypeConverter extends GenericConverter<HandleType, HandleClasses> {

    public HandleTypeConverter(Function<HandleType, HandleClasses> fromConvert, Function<HandleClasses, HandleType> toConvert) {
        super(fromConvert, toConvert);
    }

}
