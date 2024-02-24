package org.khasanof.models.matcher;

import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.models.matcher
 * @since 2/23/2024 12:06 AM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MatcherProperty {

    private Enum property;
    private PropertyFunction function;

}
