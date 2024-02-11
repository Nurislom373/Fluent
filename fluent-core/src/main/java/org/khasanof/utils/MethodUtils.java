package org.khasanof.utils;

import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.utils
 * @since 06.07.2023 21:28
 */
public abstract class MethodUtils {

    public static void tryAccessWhenMethodNotPublic(Method invokerMethod) {
        if (!Modifier.isPublic(invokerMethod.getModifiers())) {
            invokerMethod.trySetAccessible();
        }
    }

    public static Method getClassMethodByName(Object o, String name) {
        return Arrays.stream(o.getClass().getDeclaredMethods())
                .peek(method -> method.setAccessible(true))
                .filter(method -> method.getName().equals(name))
                .findFirst().orElse(null);
    }

    public static Object[] sorter(Object[] objects, Class<?>[] parameterTypes) {
        return Arrays.stream(objects).filter(any -> Arrays.stream(parameterTypes)
                        .anyMatch(param -> param.equals(any.getClass()) ||
                                param.isAssignableFrom(any.getClass())))
                .toArray();
    }

    public static Object[] sorterV2(Object[] objects, Class<?>[] parameterTypes) {
        List<Object> list = new ArrayList<>();
        Arrays.stream(parameterTypes)
                .forEach(parameterType -> {
                    for (Object arg : objects) {
                        if (parameterType.equals(arg.getClass()) ||
                                parameterType.isAssignableFrom(arg.getClass())) {
                            list.add(arg);
                        }
                    }
                });
        return list.toArray();
    }

    public static Object[] cleaner(Object[] objects, Constructor<?> constructor) {
        Object[] newObjects = new Object[objects.length];
        int count = 0;
        for (Object object : objects) {
            for (Class<?> parameterType : constructor.getParameterTypes()) {
                if (object.getClass().equals(parameterType) || (object.getClass().equals(Throwable.class) &&
                        parameterType.isAssignableFrom(object.getClass()))) {
                    newObjects[count] = object;
                    count++;
                }
            }
        }
        return newObjects;
    }

    public static Object[] cleanerV2(Object[] args) {
        return Arrays.stream(args).filter(Objects::nonNull).toArray();
    }

    @SuppressWarnings("unchecked")
    public static <T> T getArg(Object[] args, Class<T> clazz) {
        return (T) Arrays.stream(args).filter(arg -> arg.getClass().equals(clazz) ||
                        clazz.isAssignableFrom(arg.getClass()))
                .findFirst().orElse(null);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getArg(List<Object> args, Class<T> clazz) {
        return (T) args.stream().filter(arg -> arg.getClass().equals(clazz) ||
                        clazz.isAssignableFrom(arg.getClass()))
                .findFirst().orElse(null);
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public static <T> T createInstanceDefaultConstructor(Class<T> clazz) {
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        Constructor<?> defaultConstructor = getDefaultConstructor(declaredConstructors);
        if (Objects.nonNull(defaultConstructor)) {
            defaultConstructor.setAccessible(true);
            return (T) defaultConstructor.newInstance();
        }
        return null;
    }

    private static Constructor<?> getDefaultConstructor(Constructor<?>[] constructors) {
        return Arrays.stream(constructors)
                .filter(constructor -> constructor.getParameterCount() == 0)
                .findFirst().orElse(null);
    }

    @SneakyThrows
    public static <T> T argsToClass(Object[] objects, Class<T> clazz) {
        long count = Arrays.stream(objects)
                .filter(Objects::nonNull).count();
        System.out.println("count = " + count);
        System.out.println("objects.length = " + objects.length);
        int length = clazz.getDeclaredConstructors().length;
        System.out.println("length = " + length);

        Constructor<?> constructorz = Arrays.stream(clazz.getDeclaredConstructors())
                .peek(constructor -> System.out.println("Con param size : " + constructor.getParameterCount()))
                .filter(constructor -> {
                    long counted = Arrays.stream(cleaner(objects, constructor))
                            .filter(Objects::nonNull)
                            .count();
                    System.out.println("counted = " + counted);
                    return counted == count;
                })
                .findFirst().orElse(null);

        System.out.println("constructorz.getParameterCount() = " + constructorz.getParameterCount());

        if (Objects.nonNull(constructorz)) {
            constructorz.setAccessible(true);
            Object[] sorter = sorter(objects, constructorz.getParameterTypes());
            for (Object o : sorter) {
                System.out.println("o = " + o);
            }
            return (T) constructorz.newInstance(sorter);
        } else {
            throw new RuntimeException("Constructor not found!");
        }
    }

    public static Object[] classConvertToObjects(Object object) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .peek(fld -> fld.setAccessible(true))
                .map(fld -> {
                    try {
                        return fld.get(object);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }).toList().toArray();
    }

    public static boolean checkInstance(Class<?> aClass) {
        boolean isInterface = false, isAbstract = false;
        if (aClass.isInterface()) {
            isInterface = true;
        }
        if (Modifier.isAbstract(aClass.getModifiers())) {
            isAbstract = true;
        }
        return !isInterface && !isAbstract;
    }

}
