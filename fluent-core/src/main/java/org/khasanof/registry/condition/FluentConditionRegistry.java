package org.khasanof.registry.condition;

import org.khasanof.feature.condition.FluentCondition;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.registry.condition
 * @since 2/28/2024 12:37 AM
 */
public interface FluentConditionRegistry {

    /**
     *
     * @return
     */
    Set<FluentCondition> getFluentConditions();

    /**
     *
     * @param conditionClass
     * @return
     */
    boolean contains(Class<?> conditionClass);

    /**
     *
     * @param conditionClass
     * @return
     */
    Optional<FluentCondition> findByType(Class<?> conditionClass);

    /**
     *
     * @param conditions
     */
    void addFluentConditions(Set<FluentCondition> conditions);

    /**
     *
     * @param condition
     */
    void addFluentCondition(FluentCondition condition);
}
