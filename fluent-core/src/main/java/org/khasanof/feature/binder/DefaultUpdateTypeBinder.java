package org.khasanof.feature.binder;

import lombok.*;
import org.khasanof.models.executors.UpdateType;

import java.lang.annotation.Annotation;

/**
 * @author Nurislom
 * @see org.khasanof.feature
 * @since 2/19/2024 12:24 AM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DefaultUpdateTypeBinder implements UpdateTypeBinder {

    private Class<? extends Annotation> annotation;
    private UpdateType updateType;

}
