package org.khasanof.service.runnable.condition;

import org.khasanof.feature.condition.FluentCondition;
import org.khasanof.registry.condition.FluentConditionRegistry;
import org.khasanof.service.FindBeansOfTypeService;
import org.khasanof.service.runnable.PostProcessor;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * @author Nurislom
 * @see org.khasanof.service.runnable.condition
 * @since 2/28/2024 10:36 PM
 */
@Service
public class FluentBeanConditionPostProcessor implements PostProcessor {

    private final FindBeansOfTypeService findBeansOfTypeService;
    private final FluentConditionRegistry fluentConditionRegistry;

    public FluentBeanConditionPostProcessor(FindBeansOfTypeService findBeansOfTypeService,
                                            FluentConditionRegistry fluentConditionRegistry) {

        this.findBeansOfTypeService = findBeansOfTypeService;
        this.fluentConditionRegistry = fluentConditionRegistry;
    }

    @Override
    public void run() {
        fluentConditionRegistry.addFluentConditions(new HashSet<>(findBeansOfTypeService.findAllByList(FluentCondition.class)));
    }
}
