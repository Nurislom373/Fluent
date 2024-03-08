package org.khasanof.feature.condition;

import org.khasanof.annotation.ConditionOnState;
import org.khasanof.annotation.ConditionOnStates;
import org.khasanof.context.FluentContextHolder;
import org.khasanof.custom.attributes.Attributes;
import org.khasanof.models.meta.AnnotatedTypeMetadata;
import org.khasanof.state.repository.StateRepositoryStrategy;
import org.khasanof.utils.UpdateUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author Nurislom
 * @see org.khasanof.feature.condition
 * @since 3/5/2024 10:27 PM
 */
@Component
@SuppressWarnings({"unchecked", "rawtypes"})
public class FluentStateCondition implements FluentCondition {

    private final StateRepositoryStrategy stateRepositoryStrategy;

    public FluentStateCondition(StateRepositoryStrategy stateRepositoryStrategy) {
        this.stateRepositoryStrategy = stateRepositoryStrategy;
    }

    @Override
    public boolean matches(Attributes attributes, AnnotatedTypeMetadata metadata) {
        String currentUserState = currentUserState();
        if (currentUserState == null) {
            return false;
        }
        return internalMatch(currentUserState, metadata);
    }

    private boolean internalMatch(String currentUserState, AnnotatedTypeMetadata annotatedTypeMetadata) {
        if (Objects.equals(annotatedTypeMetadata.getAnnotation().annotationType(), ConditionOnStates.class)) {
            return repeatableEvaluate(annotatedTypeMetadata.getAnnotationCast(), currentUserState);
        }
        return defaultEvaluate(annotatedTypeMetadata.getAnnotationCast(), currentUserState);
    }

    private boolean repeatableEvaluate(ConditionOnStates conditionOnStates, String currentUserState) {
        return Arrays.stream(conditionOnStates.value())
                .allMatch(conditionOnState -> defaultEvaluate(conditionOnState, currentUserState));
    }

    private boolean defaultEvaluate(ConditionOnState conditionOnState, String currentUserState) {
        return getMatchFunction(conditionOnState.nonMatch())
                .apply(Arrays.stream(conditionOnState.value()), conditionValue -> Objects.equals(conditionValue, currentUserState));
    }

    private BiFunction<Stream, Predicate, Boolean> getMatchFunction(boolean nonMatch) {
        return nonMatch ? Stream::noneMatch : Stream::allMatch;
    }

    private String currentUserState() {
        return stateRepositoryStrategy.findById(UpdateUtils.getUserId(FluentContextHolder.getCurrentUpdate()))
                .map(state -> state.getState().toString())
                .orElse(null);
    }
}
