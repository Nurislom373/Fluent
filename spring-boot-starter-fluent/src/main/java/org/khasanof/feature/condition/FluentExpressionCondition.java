package org.khasanof.feature.condition;

import org.jetbrains.annotations.NotNull;
import org.khasanof.annotation.ConditionOnExpression;
import org.khasanof.annotation.ConditionOnExpressions;
import org.khasanof.custom.attributes.Attributes;
import org.khasanof.models.meta.AnnotatedTypeMetadata;
import org.springframework.beans.factory.config.BeanExpressionContext;
import org.springframework.beans.factory.config.BeanExpressionResolver;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.StandardBeanExpressionResolver;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.feature.condition
 * @since 2/28/2024 12:00 AM
 */
@Component
public class FluentExpressionCondition implements FluentCondition {

    private final ApplicationContext applicationContext;

    public FluentExpressionCondition(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public boolean matches(Attributes attributes, AnnotatedTypeMetadata metadata) {
        if (Objects.equals(metadata.getAnnotation().annotationType(), ConditionOnExpressions.class)) {
            return repeatableEvaluate(metadata);
        }
        return defaultEvaluate(metadata);
    }

    private boolean repeatableEvaluate(AnnotatedTypeMetadata metadata) {
        ConfigurableListableBeanFactory beanFactory = getAutowireCapableBeanFactory();
        ConditionOnExpressions conditions = metadata.getAnnotationCast();
        return Arrays.stream(conditions.value())
                .anyMatch(condition -> evaluateExpression(beanFactory, wrapIfNecessary(condition.value())));
    }

    private boolean defaultEvaluate(AnnotatedTypeMetadata metadata) {
        ConfigurableListableBeanFactory beanFactory = getAutowireCapableBeanFactory();
        ConditionOnExpression condition = metadata.getAnnotationCast();
        return evaluateExpression(beanFactory, wrapIfNecessary(condition.value()));
    }

    @NotNull
    private ConfigurableListableBeanFactory getAutowireCapableBeanFactory() {
        return (ConfigurableListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
    }

    private boolean evaluateExpression(ConfigurableListableBeanFactory beanFactory, String expression) {
        BeanExpressionResolver resolver = beanFactory.getBeanExpressionResolver();
        if (resolver == null) {
            resolver = new StandardBeanExpressionResolver();
        }
        BeanExpressionContext expressionContext = new BeanExpressionContext(beanFactory, null);
        Object result = resolver.evaluate(expression, expressionContext);
        return (result != null && (boolean) result);
    }

    private String wrapIfNecessary(String expression) {
        if (!expression.startsWith("#{")) {
            return "#{" + expression + "}";
        }
        return expression;
    }
}
