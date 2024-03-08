package org.khasanof.models.invoker;

import lombok.Getter;

/**
 * @author Nurislom
 * @see org.khasanof.models.invoker
 * @since 1/24/2024 10:56 PM
 */
@Getter
public enum InvokerParam {

    METHOD_TYPE,

    METHOD_PARAMETER_TYPES,

    ADDITIONAL_PARAM,

    ANNOTATION,

    ANNOTATION_TYPE,

    CONDITIONS,

    CONDITIONS_METHOD,

    OTHER
}
