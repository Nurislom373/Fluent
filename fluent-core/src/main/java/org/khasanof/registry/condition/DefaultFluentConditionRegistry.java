package org.khasanof.registry.condition;

import org.khasanof.feature.condition.FluentCondition;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;

/**
 * @author Nurislom
 * @see org.khasanof.registry.condition
 * @since 2/28/2024 12:38 AM
 */
public class DefaultFluentConditionRegistry implements FluentConditionRegistry {

    private final Set<FluentCondition> conditions = new HashSet<>();

    @Override
    public Set<FluentCondition> getFluentConditions() {
        return this.conditions;
    }

    @Override
    public boolean contains(Class<?> conditionClass) {
        return this.conditions.stream()
                .anyMatch(getConditionPredicate(conditionClass));
    }

    @Override
    public Optional<FluentCondition> findByType(Class<?> conditionClass) {
        return this.conditions.stream()
                .filter(getConditionPredicate(conditionClass))
                .findFirst();
    }

    @Override
    public void addFluentConditions(Set<FluentCondition> conditions) {
        if (Objects.nonNull(conditions)) {
            this.conditions.addAll(conditions);
        }
    }

    @Override
    public void addFluentCondition(FluentCondition condition) {
        if (Objects.nonNull(condition)) {
            this.conditions.add(condition);
        }
    }

    private Predicate<FluentCondition> getConditionPredicate(Class<?> conditionClass) {
        return fluentCondition -> Objects.equals(fluentCondition.getClass(), conditionClass);
    }
}
