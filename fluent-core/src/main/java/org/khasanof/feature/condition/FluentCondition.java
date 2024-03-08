package org.khasanof.feature.condition;

import org.khasanof.custom.attributes.Attributes;
import org.khasanof.models.meta.AnnotatedTypeMetadata;

/**
 * @author Nurislom
 * @see org.khasanof.feature.condition
 * @since 2/26/2024 10:35 PM
 */
public interface FluentCondition {

    /**
     *
     * @param attributes
     * @param metadata
     * @return
     */
    boolean matches(Attributes attributes, AnnotatedTypeMetadata metadata);
}
