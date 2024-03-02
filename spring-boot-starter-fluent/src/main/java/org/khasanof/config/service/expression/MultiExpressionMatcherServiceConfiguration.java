package org.khasanof.config.service.expression;

import org.khasanof.enums.RepeatableMatchType;
import org.khasanof.models.matcher.RepeatableMatcherModel;
import org.khasanof.service.expression.DefaultMultiExpressionMatcherService;
import org.khasanof.service.expression.MultiExpressionMatcherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.stream.Stream;

/**
 * @author Nurislom
 * @see org.khasanof.config.service.expression
 * @since 3/2/2024 4:17 PM
 */
@Configuration
public class MultiExpressionMatcherServiceConfiguration {

    /**
     *
     * @return {@link MultiExpressionMatcherService} bean
     */
    @Bean
    public MultiExpressionMatcherService multiExpressionMatcherService() {
        DefaultMultiExpressionMatcherService expressionMatcherService = new DefaultMultiExpressionMatcherService();
        expressionMatcherService.addMatchers(repeatableMatcherModels());
        return expressionMatcherService;
    }

    private Set<RepeatableMatcherModel> repeatableMatcherModels() {
        return Set.of(
            new RepeatableMatcherModel(RepeatableMatchType.ALL_MATCH, Stream::allMatch),

            new RepeatableMatcherModel(RepeatableMatchType.NONE_MATCH, Stream::noneMatch),

            new RepeatableMatcherModel(RepeatableMatchType.ANY_MATCH, Stream::anyMatch)
        );
    }
}
