package org.khasanof.executors.matcher;

import java.lang.annotation.Annotation;

import static org.khasanof.utils.BaseUtils.getFirstGenericType;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 03.07.2023 17:47
 */
public interface AbstractMatcher<T extends Annotation, V> {

    /**
     *
     * @param annotation
     * @param value
     * @return
     */
    boolean matcher(T annotation, V value);

    /**
     *
     * @return
     */
    default Class<T> getType() {
        return getFirstGenericType(getClass());
    }
}
