package org.khasanof.models.matcher;

import lombok.*;
import org.khasanof.enums.MatchType;

import java.util.Objects;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.models.matcher
 * @since 2/23/2024 12:11 AM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MatcherParameters {

    private Object value;
    private Enum property;
    private MatchType matchType;
    private String annotationValue;
    private Set<MatcherProperty> matcherProperties;

    public boolean hasProperties() {
        return Objects.nonNull(matcherProperties);
    }
}
