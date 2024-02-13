package org.khasanof.method;

import org.khasanof.service.template.operations.SendTextOperations;
import org.khasanof.service.template.operations.callback.AnswerCallbackQueryOperations;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Nurislom
 * @see org.khasanof.method
 * @since 1/9/2024 12:09 AM
 */
public class DefaultExecuteMethodReflect implements ExecuteMethodReflect {

    private final Set<String> ignoreMethodNames = Set.of("toString", "equals", "hashCode");

    @Override
    public Set<Method> getMethodWithString() {
        return getClasses()
                .stream()
                .flatMap(clazz -> Arrays.stream(clazz.getMethods())
                        .filter(method -> Modifier.isPublic(method.getModifiers()))
                        .filter(method -> !ignoreMethodNames.contains(method.getName()))
                ).collect(Collectors.toSet());
    }

    @Override
    public Set<Method> getPublicMethods(Class<?> clazz) {
        return Arrays.stream(clazz.getMethods())
                .filter(method -> Modifier.isPublic(method.getModifiers()))
                .filter(method -> !ignoreMethodNames.contains(method.getName()))
                .collect(Collectors.toSet());
    }

    private Set<Class<?>> getClasses() {
        return Set.of(
                SendTextOperations.class,
                AnswerCallbackQueryOperations.class
        );
    }
}
