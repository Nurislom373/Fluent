package org.khasanof.collector.method.checker;

import org.junit.jupiter.api.Test;
import org.khasanof.annotation.exception.HandleException;
import org.khasanof.exceptions.InvalidParamsException;
import org.khasanof.factories.method.ExceptionMethodCheckConditionFactory;
import org.khasanof.factories.method.ExceptionMethodCheckConditionFactoryImpl;
import org.khasanof.factories.method.HandleAnyMethodCheckConditionFactory;
import org.khasanof.factories.method.HandleAnyMethodCheckConditionFactoryImpl;
import org.springframework.util.ReflectionUtils;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Nurislom
 * @see org.khasanof.collector.method.checker
 * @since 1/30/2024 11:31 PM
 */
public class ExceptionHandleMethodCheckerTest {

    private final ProcessTypeHandleMethodChecker handleMethodChecker = createHandleMethodChecker();

    @Test
    void firstTestCheckMethodShouldSuccess() {
        Method handleMethod = getValidHandleMethodFirst();
        assertTrue(handleMethodChecker.check(handleMethod));
    }

    @Test
    void secondTestCheckMethodShouldSuccess() {
        Method handleMethod = getValidHandleMethodSecond();
        assertTrue(handleMethodChecker.check(handleMethod));
    }

    @Test
    void firstTestCheckMethodShouldFailed() {
        Method handleMethod = getInvalidHandleMethodSecond();
        assertThrows(InvalidParamsException.class, () -> handleMethodChecker.check(handleMethod));
    }

    ExceptionHandleMethodChecker createHandleMethodChecker() {
        return new ExceptionHandleMethodChecker(createConditionFactory());
    }

    ExceptionMethodCheckConditionFactory createConditionFactory() {
        return new ExceptionMethodCheckConditionFactoryImpl();
    }

    private Method getInvalidHandleMethodSecond() {
        return ReflectionUtils.findMethod(TestController.class, "handleException", Update.class, AbsSender.class, RuntimeException.class);
    }

    private Method getValidHandleMethodFirst() {
        return ReflectionUtils.findMethod(TestController.class, "handleException", Update.class, RuntimeException.class);
    }

    private Method getValidHandleMethodSecond() {
        return ReflectionUtils.findMethod(TestController.class, "handleException", RuntimeException.class);
    }

    public static class TestController {

        @HandleException({RuntimeException.class})
        void handleException(RuntimeException ex) {
        }

        @HandleException({RuntimeException.class})
        void handleException(Update update, RuntimeException ex) {
        }

        @HandleException({RuntimeException.class})
        void handleException(Update update, AbsSender sender, RuntimeException ex) {
        }

    }

}
