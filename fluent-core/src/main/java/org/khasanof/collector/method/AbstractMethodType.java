package org.khasanof.collector.method;

import java.lang.annotation.Annotation;

/**
 * @author Nurislom
 * @see org.khasanof.collector.method
 * @since 20.07.2023 10:34
 */
public interface AbstractMethodType {

    Class<? extends Annotation> getType();

    boolean hasSuperAnnotation();

    boolean hasAny();

}
