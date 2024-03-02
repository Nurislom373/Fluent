package org.khasanof.models.matcher;

import lombok.*;
import org.khasanof.enums.RepeatableMatchType;
import org.khasanof.executors.matcher.GenericMatcher;

import java.lang.annotation.Annotation;

/**
 * @author Nurislom
 * @see org.khasanof.models.matcher
 * @since 3/2/2024 4:13 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RepeatableMatcherParameters {

    private RepeatableMatchType matchType;
    private Annotation[] annotations;
    private GenericMatcher matcher;
    private Object value;

}
