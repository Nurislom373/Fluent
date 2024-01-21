package org.khasanof.mediator;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.annotation.process.ProcessFile;
import org.khasanof.collector.method.checker.strategy.DefaultMethodCheckOperationStrategy;
import org.khasanof.collector.method.checker.strategy.MethodCheckOperationStrategy;
import org.khasanof.collector.method.checker.strategy.ProcessFileMethodCheckOperationStrategy;
import org.khasanof.collector.method.checker.strategy.VarExpressionMethodCheckOperationStrategy;
import org.khasanof.enums.MatchScope;
import org.khasanof.utils.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.mediator
 * @since 1/21/2024 2:26 PM
 */
@Slf4j
@Component
public class DefaultMethodCheckOperationStrategyMediator implements MethodCheckOperationStrategyMediator {

    @Override
    public boolean check(Method method) {
        return getCheckOperationStrategy(method)
                .check(method);
    }

    private MethodCheckOperationStrategy getCheckOperationStrategy(Method method) {
        MethodCheckOperationStrategy checkOperationStrategy = new DefaultMethodCheckOperationStrategy();

        if (AnnotationUtils.hasAnnotation(method, ProcessFile.class, true)) {
            checkOperationStrategy = new ProcessFileMethodCheckOperationStrategy();
        }

        if (AnnotationUtils.hasAnnotation(method, HandleMessage.class, false)) {
            checkOperationStrategy = getVarExpressionMethodCheckOperationStrategy(method, checkOperationStrategy);
        }

        return checkOperationStrategy;
    }

    private MethodCheckOperationStrategy getVarExpressionMethodCheckOperationStrategy(Method method, MethodCheckOperationStrategy checkOperationStrategy) {
        HandleMessage handleMessage = method.getAnnotation(HandleMessage.class);

        if (Objects.equals(handleMessage.scope(), MatchScope.VAR_EXPRESSION)) {
            checkOperationStrategy = new VarExpressionMethodCheckOperationStrategy();
        }
        return checkOperationStrategy;
    }

}
