package org.khasanof.converter;

import org.khasanof.enums.HandleAnnotations;
import org.khasanof.enums.HandleType;

import java.util.function.Function;

/**
 * @author Nurislom
 * @see org.khasanof.converter
 * @since 1/6/2024 8:44 PM
 */
public abstract class HandleTypeConverter extends GenericConverter<HandleType, HandleAnnotations> {

    public HandleTypeConverter(Function<HandleType, HandleAnnotations> fromConvert, Function<HandleAnnotations, HandleType> toConvert) {
        super(fromConvert, toConvert);
    }

}
