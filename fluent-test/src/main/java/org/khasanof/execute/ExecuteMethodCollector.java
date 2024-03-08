package org.khasanof.execute;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/7/2024 3:23 PM
 */
public interface ExecuteMethodCollector {

    Set<Method> getMethods();

    boolean contains(Method method);

    void addMethod(Method senderMethod);

}
