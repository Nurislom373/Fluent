package org.khasanof.models.collector;

import lombok.*;

import java.lang.annotation.Annotation;

/**
 * @author Nurislom
 * @see org.khasanof.models.collector
 * @since 1/15/2024 10:54 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ContainsHandlerMethod {

    private Class<? extends Annotation> key;

}
