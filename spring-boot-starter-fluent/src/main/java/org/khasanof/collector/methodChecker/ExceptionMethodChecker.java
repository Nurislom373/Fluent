package org.khasanof.collector.methodChecker;

import org.khasanof.annotation.exception.HandleException;
import org.khasanof.exceptions.InvalidParamsException;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.collector.methodChecker
 * @since 04.07.2023 22:45
 */
@Component
public class ExceptionMethodChecker extends AbstractMethodChecker {

    private final List<Class<?>> validTypes = List.of(Throwable.class, Update.class, AbsSender.class);

    @Override
    public boolean valid(Method method) {
        boolean validParams, validAnnotation;
        Class<?>[] parameterTypes = method.getParameterTypes();

        if (method.getAnnotations().length == 0) {
            return false;
        }

        validAnnotation = hasAnnotation(method, HandleException.class);

        if (parameterTypes.length < 1 || parameterTypes.length > 3) {
            throw new InvalidParamsException("Exception handler method invalid parameters!");
        }

        validParams = allMatchParameter(parameterTypes);

        if (!validParams && validAnnotation) {
            throw new InvalidParamsException("Exception handler method invalid parameters!");
        }

        return validParams && validAnnotation;
    }

    private boolean allMatchParameter(Class<?>[] classes) {
        return Arrays.stream(classes).allMatch(clazz -> validTypes.stream()
                .anyMatch(valid -> valid.equals(clazz) || valid.isAssignableFrom(clazz)));
    }

    @Override
    public Class<? extends Annotation> getType() {
        return HandleException.class;
    }

    @Override
    public boolean hasSuperAnnotation() {
        return false;
    }

    @Override
    public boolean hasAny() {
        return true;
    }
}
