package org.khasanof.models.condition;

import java.lang.annotation.Annotation;

/**
 * @author Nurislom
 * @see org.khasanof.models.condition
 * @since 2/26/2024 10:51 PM
 */
public interface ConditionDefinition {

    Class<?> getConditionClass();

    Annotation getConditionAnnotation();

}
