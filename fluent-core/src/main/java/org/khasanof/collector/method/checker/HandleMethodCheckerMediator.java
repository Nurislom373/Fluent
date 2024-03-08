package org.khasanof.collector.method.checker;

import org.khasanof.GenericCheck;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.collector.method.checker
 * @since 12/26/2023 9:29 PM
 */
public interface HandleMethodCheckerMediator extends GenericCheck<Method> {

    /**
     *
     * @param methodCheckers
     */
    void addMethodCheckers(Set<HandleMethodChecker> methodCheckers);

    /**
     *
     * @param handleMethodChecker
     */
    void addMethodChecker(HandleMethodChecker handleMethodChecker);
}
