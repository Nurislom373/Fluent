package org.khasanof.utils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.utils
 * @since 1/9/2024 9:26 PM
 */
public class FluentTestUtils {

    /**
     * @param method
     * @param methods
     * @return
     */
    public static boolean isMatchMethod(Method method, Set<Method> methods) {
        return methods.stream()
                .anyMatch(method1 -> equalsMethods(method, method1));
    }

    /**
     * @param execMethod
     * @param method
     * @return
     */
    public static boolean equalsMethods(Method execMethod, Method method) {
        if (!Objects.equals(execMethod.getName(), method.getName())) {
            return false;
        }

        if (!Arrays.equals(execMethod.getParameterTypes(), method.getParameterTypes())) {
            return false;
        }
        return Objects.equals(execMethod.getModifiers(), method.getModifiers());
    }

    /**
     *
     * @param args
     * @param clazz
     * @return
     * @param <T>
     */
    public static  <T> Optional<T> getMatchArgument(Object[] args, Class<T> clazz) {
        return Arrays.stream(args)
                .filter(arg -> Objects.equals(arg.getClass(), clazz) || clazz.isAssignableFrom(arg.getClass()))
                .map(arg -> (T) arg)
                .findFirst();
    }

    public static  <T> Optional<T> getMatchArgument(Object[] args, Integer index, Class<T> clazz) {
        T genericClass = (T) args[index];
        if (Objects.equals(clazz, genericClass.getClass()) && clazz.isAssignableFrom(genericClass.getClass())) {
            return Optional.ofNullable(genericClass);
        }
        return Optional.empty();
    }
}
