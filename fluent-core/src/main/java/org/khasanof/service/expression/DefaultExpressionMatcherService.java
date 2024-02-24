package org.khasanof.service.expression;

import org.khasanof.enums.MatchType;
import org.khasanof.models.matcher.MatcherModel;
import org.khasanof.models.matcher.MatcherParameters;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.service.expression
 * @since 2/22/2024 11:27 PM
 */
@SuppressWarnings({"unchecked"})
public class DefaultExpressionMatcherService implements ExpressionMatcherService {

    private final Set<MatcherModel> models = new HashSet<>();

    @Override
    public boolean match(MatchType matchType, String annotationValue, Object value) {
        return findMatcher(matchType, getObjectType(value, matchType))
                .map(matcherModel -> matcherModel.applyFunction(annotationValue, value))
                .orElse(false);
    }

    @Override
    public boolean match(MatcherParameters parameters) {
        return getPropertyValue(parameters)
                .map(value -> findMatcher(parameters.getMatchType(), getObjectType(value, parameters.getMatchType()))
                        .map(matcherModel -> matcherModel.applyFunction(parameters.getAnnotationValue(), value))
                        .orElse(false))
                .orElse(false);
    }

    protected Optional<Object> getPropertyValue(MatcherParameters parameters) {
        if (parameters.hasProperties()) {
            return getPropertyValueInternal(parameters);
        }
        return Optional.of(parameters.getValue());
    }

    private Optional<Object> getPropertyValueInternal(MatcherParameters parameters) {
        return parameters.getMatcherProperties()
                .stream()
                .filter(matcherProperty -> Objects.equals(matcherProperty.getProperty(), parameters.getProperty()))
                .map(matcherProperty -> matcherProperty.getFunction().apply(parameters.getValue()))
                .findFirst();
    }

    protected Class<?> getObjectType(Object type, MatchType matchType) {
        if (matchType.equals(MatchType.EXPRESSION) || matchType.equals(MatchType.REGEX)) {
            return Object.class;
        }
        return type.getClass();
    }

    protected Optional<MatcherModel> findMatcher(MatchType matchType, Class<?> objectType) {
        return this.models.stream()
                .filter(model -> model.equals(matchType, objectType))
                .findFirst();
    }

    @Override
    public void addMatchers(Set<MatcherModel> models) {
        if (Objects.nonNull(models)) {
            this.models.addAll(models);
        }
    }

    @Override
    public void addMatcher(MatcherModel model) {
        if (Objects.nonNull(model)) {
            this.models.add(model);
        }
    }
}
