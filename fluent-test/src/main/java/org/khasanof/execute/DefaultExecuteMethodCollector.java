package org.khasanof.execute;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.BooleanSupplier;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/7/2024 3:25 PM
 */
public class DefaultExecuteMethodCollector implements ExecuteMethodCollector {

    private final Set<Method> methods = new HashSet<>();

    @Override
    public Set<Method> getMethods() {
        return this.methods;
    }

    @Override
    public boolean contains(Method method) {
        return this.methods.contains(method);
    }

    @Override
    public void addMethod(Method method) {
        checkAndExecute(() -> Objects.nonNull(method), () -> this.methods.add(method));
    }

    private void checkAndExecute(BooleanSupplier supplier, Runnable runnable) {
        if (supplier.getAsBoolean())
            runnable.run();
    }
}
