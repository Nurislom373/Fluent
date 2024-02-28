package org.khasanof.collector.method.checker;

import org.khasanof.GenericCheck;
import org.khasanof.models.condition.MethodGenericCondition;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.collector.method.checker
 * @since 12/26/2023 9:17 PM
 */
public interface HandleMethodChecker extends GenericCheck<Method> {

    Set<MethodGenericCondition> conditions();

}
