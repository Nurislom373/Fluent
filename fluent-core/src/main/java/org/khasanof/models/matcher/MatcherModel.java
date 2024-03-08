package org.khasanof.models.matcher;

import lombok.*;
import org.khasanof.enums.MatchType;
import org.khasanof.models.matcher.function.MatcherFunction;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.models.matcher
 * @since 2/22/2024 11:22 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MatcherModel {

    private MatchType matchType;
    private Class<?> objectType;
    private MatcherFunction function;

    /**
     *
     * @param matchType
     * @param objectType
     * @return
     */
    public boolean equals(MatchType matchType, Class<?> objectType) {
        return Objects.equals(this.matchType, matchType) && Objects.equals(this.objectType, objectType);
    }

    /**
     *
     * @param o1
     * @param o2
     * @return
     */
    public boolean applyFunction(Object o1, Object o2) {
        return this.function.apply(o1, o2);
    }
}
