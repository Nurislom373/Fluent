package org.khasanof.service.expression;

import org.khasanof.models.matcher.RepeatableMatcherModel;
import org.khasanof.models.matcher.RepeatableMatcherParameters;

import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.service.expression
 * @since 3/2/2024 3:59 PM
 */
public interface MultiExpressionMatcherService {

    /**
     *
     * @param parameters
     * @return
     */
    boolean match(RepeatableMatcherParameters parameters);

    /**
     *
     * @param models
     */
    void addMatchers(Set<RepeatableMatcherModel> models);

    /**
     *
     * @param model
     */
    void addMatcher(RepeatableMatcherModel model);
}
