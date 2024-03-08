package org.khasanof.service.expression;

import org.jetbrains.annotations.NotNull;
import org.khasanof.enums.RepeatableMatchType;
import org.khasanof.executors.matcher.GenericMatcher;
import org.khasanof.models.matcher.RepeatableMatcherModel;
import org.khasanof.models.matcher.RepeatableMatcherParameters;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * @author Nurislom
 * @see org.khasanof.service.expression
 * @since 3/2/2024 4:06 PM
 */
public class DefaultMultiExpressionMatcherService implements MultiExpressionMatcherService {

    private final Set<RepeatableMatcherModel> models = new HashSet<>();

    @Override
    public boolean match(RepeatableMatcherParameters parameters) {
        return getMatcherModel(parameters.getMatchType())
                .map(model -> applyFunction(parameters.getAnnotations(), parameters.getMatcher(), parameters.getValue(), model))
                .orElse(false);
    }

    private Boolean applyFunction(Annotation[] annotations, GenericMatcher matcher, Object value, RepeatableMatcherModel model) {
        return model.getFunction()
                .apply(Arrays.stream(annotations), (ann -> matcher.matcher((Annotation) ann, value)));
    }

    @NotNull
    private Optional<RepeatableMatcherModel> getMatcherModel(RepeatableMatchType matchType) {
        return this.models.stream()
                .filter(model -> Objects.equals(model.getMatchType(), matchType))
                .findFirst();
    }

    @Override
    public void addMatchers(Set<RepeatableMatcherModel> models) {
        if (Objects.nonNull(models)) {
            this.models.addAll(models);
        }
    }

    @Override
    public void addMatcher(RepeatableMatcherModel model) {
        if (Objects.nonNull(model)) {
            this.models.add(model);
        }
    }
}
