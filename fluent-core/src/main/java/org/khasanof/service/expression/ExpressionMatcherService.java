package org.khasanof.service.expression;

import org.khasanof.enums.MatchType;
import org.khasanof.models.matcher.MatcherModel;
import org.khasanof.models.matcher.MatcherParameters;

import java.util.List;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.service.expression
 * @since 2/22/2024 11:18 PM
 */
public interface ExpressionMatcherService {

    /**
     *
     * @param matchType
     * @param value
     * @return
     */
    boolean match(MatchType matchType, String annotationValue, Object value);

    /**
     *
     * @param parameters
     * @return
     */
    boolean match(MatcherParameters parameters);

    /**
     *
     * @param models
     */
    void addMatchers(Set<MatcherModel> models);

    /**
     *
     * @param model
     */
    void addMatcher(MatcherModel model);
}
