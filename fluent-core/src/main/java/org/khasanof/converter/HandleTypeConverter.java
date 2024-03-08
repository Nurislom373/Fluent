package org.khasanof.converter;

import org.khasanof.enums.HandleAnnotation;
import org.khasanof.enums.HandleType;

import java.util.function.Function;

/**
 * @author Nurislom
 * @see org.khasanof.converter
 * @since 1/6/2024 8:44 PM
 */
public abstract class HandleTypeConverter extends GenericConverter<HandleType, HandleAnnotation> {

    public HandleTypeConverter(Function<HandleType, HandleAnnotation> fromConvert, Function<HandleAnnotation, HandleType> toConvert) {
        super(fromConvert, toConvert);
    }

}
