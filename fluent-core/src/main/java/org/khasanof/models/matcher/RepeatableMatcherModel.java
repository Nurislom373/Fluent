package org.khasanof.models.matcher;

import lombok.*;
import org.khasanof.enums.RepeatableMatchType;
import org.khasanof.models.matcher.function.RepeatableMatcherFunction;

/**
 * @author Nurislom
 * @see org.khasanof.models.matcher
 * @since 3/2/2024 4:01 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RepeatableMatcherModel {

    private RepeatableMatchType matchType;
    private RepeatableMatcherFunction function;

}
