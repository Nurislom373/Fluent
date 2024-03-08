package org.khasanof.method;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.method
 * @since 1/9/2024 12:09 AM
 */
public interface ExecuteMethodReflect {

    Set<Method> getMethodWithString();

    Set<Method> getPublicMethods(Class<?> clazz);
}
