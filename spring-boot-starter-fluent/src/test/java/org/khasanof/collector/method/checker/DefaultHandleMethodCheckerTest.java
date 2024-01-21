package org.khasanof.collector.method.checker;

import org.junit.jupiter.api.Test;
import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.enums.MatchScope;
import org.khasanof.exceptions.InvalidParamsException;
import org.khasanof.factories.method.DefaultMethodCheckConditionFactoryImpl;
import org.khasanof.factories.method.DefaultMethodCheckConditionFactory;
import org.khasanof.mediator.DefaultMethodCheckOperationStrategyMediator;
import org.springframework.util.ReflectionUtils;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Nurislom
 * @see org.khasanof.collector.method.checker
 * @since 12/26/2023 10:58 PM
 */
public class DefaultHandleMethodCheckerTest {

    private final ProcessTypeHandleMethodChecker handleMethodChecker = createHandleMethodChecker();

    @Test
    void testCheckMethodShouldSuccess() {
        Method handleMethod = getValidHandleMethod();
        assertTrue(handleMethodChecker.check(handleMethod));
    }

    @Test
    void testCheckMethodShouldFailed() {
        Method invalidHandleMethod = getInvalidHandleMethod();
        assertThrows(InvalidParamsException.class, () -> handleMethodChecker.check(invalidHandleMethod));
    }

    private Method getInvalidHandleMethod() {
        return ReflectionUtils.findMethod(TestController.class, "invalidHandleMethod",
                AbsSender.class, Update.class, String.class);
    }

    private Method getValidHandleMethod() {
        return ReflectionUtils.findMethod(TestController.class, "validHandleMethod",
                AbsSender.class, Update.class);
    }

    ProcessTypeHandleMethodChecker createHandleMethodChecker() {
        return new DefaultHandleMethodChecker(createConditionFactory(), new DefaultMethodCheckOperationStrategyMediator());
    }

    DefaultMethodCheckConditionFactory createConditionFactory() {
        return new DefaultMethodCheckConditionFactoryImpl();
    }

    public static class TestController {

        @HandleMessage(value = "/start", scope = MatchScope.EQUALS)
        public void validHandleMethod(AbsSender sender, Update update) {}

        @HandleMessage(value = "/invalid", scope = MatchScope.EQUALS)
        public void invalidHandleMethod(AbsSender sender, Update update, String test) {}

    }

}
