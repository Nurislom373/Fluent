package org.khasanof.collector.method.checker;

import org.junit.jupiter.api.Test;
import org.khasanof.annotation.methods.HandleAny;
import org.khasanof.enums.HandleType;
import org.khasanof.enums.Proceed;
import org.khasanof.exceptions.InvalidParamsException;
import org.khasanof.factories.method.HandleAnyMethodCheckConditionFactory;
import org.khasanof.factories.method.HandleAnyMethodCheckConditionFactoryImpl;
import org.springframework.util.ReflectionUtils;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Nurislom
 * @see org.khasanof.collector.method.checker
 * @since 12/27/2023 10:26 PM
 */
public class AnyHandleMethodCheckerTest {

    private final ProcessTypeHandleMethodChecker handleMethodChecker = createHandleMethodChecker();

    @Test
    void firstTestCheckMethodShouldSuccess() {
        Method handleMethod = getValidFirstMethod();
        assertTrue(handleMethodChecker.check(handleMethod));
    }

    @Test
    void secondTestCheckMethodShouldFailed() {
        Method handleMethod = getInvalidFirstMethod();
        assertThrows(InvalidParamsException.class, () -> handleMethodChecker.check(handleMethod));
    }

    @Test
    void thirdTestCheckMethodShouldSuccess() {
        Method handleMethod = getValidSecondMethod();
        assertTrue(handleMethodChecker.check(handleMethod));
    }

    @Test
    void fourthTestCheckMethodShouldFailed() {
        Method handleMethod = getInvalidSecondMethod();
        assertThrows(InvalidParamsException.class, () -> handleMethodChecker.check(handleMethod));
    }

    @Test
    void fifthTestCheckMethodShouldFailed() {
        Method handleMethod = getInvalidThirdMethod();
        assertThrows(InvalidParamsException.class, () -> handleMethodChecker.check(handleMethod));
    }

    @Test
    void sixthTestCheckMethodShouldNotSuccess() {
        Method handleMethod = getCheckFalseMethod();
        assertFalse(handleMethodChecker.check(handleMethod));
    }

    private Method getCheckFalseMethod() {
        return ReflectionUtils.findMethod(AnyHandleMethodCheckerTest.TestController.class, "invalidFourth");
    }

    private Method getInvalidFirstMethod() {
        return ReflectionUtils.findMethod(AnyHandleMethodCheckerTest.TestController.class, "invalidFirst",
                Update.class, AbsSender.class, String.class);
    }

    private Method getInvalidSecondMethod() {
        return ReflectionUtils.findMethod(AnyHandleMethodCheckerTest.TestController.class, "invalidSecond",
                String.class, AbsSender.class);
    }

    private Method getInvalidThirdMethod() {
        return ReflectionUtils.findMethod(AnyHandleMethodCheckerTest.TestController.class, "invalidThird",
                AbsSender.class, String.class);
    }

    private Method getValidFirstMethod() {
        return ReflectionUtils.findMethod(AnyHandleMethodCheckerTest.TestController.class, "validFirst",
                Update.class, AbsSender.class);
    }

    private Method getValidSecondMethod() {
        return ReflectionUtils.findMethod(AnyHandleMethodCheckerTest.TestController.class, "validSecond");
    }

    ProcessTypeHandleMethodChecker createHandleMethodChecker() {
        return new HandleAnyMethodChecker(createConditionFactory());
    }

    HandleAnyMethodCheckConditionFactory createConditionFactory() {
        return new HandleAnyMethodCheckConditionFactoryImpl();
    }

    public static class TestController {

        // valid
        @HandleAny(type = HandleType.MESSAGE, proceed = Proceed.PROCEED)
        private void validFirst(Update update, AbsSender sender) {}

        // valid
        @HandleAny(type = HandleType.AUDIO)
        private void validSecond() {}

        // invalid
        @HandleAny(type = HandleType.PHOTO, proceed = Proceed.PROCEED)
        private void invalidFirst(Update update, AbsSender sender, String test) {}

        // invalid
        @HandleAny(type = HandleType.MESSAGE, proceed = Proceed.PROCEED)
        private void invalidSecond(String update, AbsSender sender) {}

        // invalid
        @HandleAny(type = HandleType.MESSAGE, proceed = Proceed.PROCEED)
        private void invalidThird(AbsSender sender, String update) {}

        // check false
        private void invalidFourth() {}
    }
}
