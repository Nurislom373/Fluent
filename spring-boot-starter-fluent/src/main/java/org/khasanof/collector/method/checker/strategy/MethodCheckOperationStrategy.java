package org.khasanof.collector.method.checker.strategy;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.collector.method.checker.strategy
 * @since 1/21/2024 2:19 PM
 */
public interface MethodCheckOperationStrategy {

    boolean check(Method method);

}
