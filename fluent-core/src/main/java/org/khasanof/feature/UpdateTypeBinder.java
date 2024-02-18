package org.khasanof.feature;

import org.khasanof.models.executors.UpdateType;

import java.lang.annotation.Annotation;

/**
 * @author Nurislom
 * @see org.khasanof.feature
 * @since 2/18/2024 4:57 PM
 */
public interface UpdateTypeBinder {

    /**
     *
     * @return
     */
    UpdateType getUpdateType();

    /**
     *
     * @return
     */
    Class<? extends Annotation> getAnnotation();
}
