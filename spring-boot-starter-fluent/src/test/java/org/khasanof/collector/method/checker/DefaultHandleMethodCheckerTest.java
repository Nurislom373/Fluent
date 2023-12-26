package org.khasanof.collector.method.checker;

import org.junit.jupiter.api.Test;
import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.enums.MatchScope;
import org.khasanof.factories.method.DefaultHandleMethodCheckerConditionFactory;
import org.khasanof.factories.method.HandleMethodCheckerConditionFactory;
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

    // TODO fixed method logic
    @Test
    void testCheckMethodShouldFailed() {
        Method invalidHandleMethod = getInvalidHandleMethod();
        assertFalse(handleMethodChecker.check(invalidHandleMethod));
    }

    private Method getInvalidHandleMethod() {
        return ReflectionUtils.findMethod(TestControllerClass.class, "invalidHandleMethod",
                AbsSender.class, Update.class, String.class);
    }

    private Method getValidHandleMethod() {
        return ReflectionUtils.findMethod(TestControllerClass.class, "validHandleMethod",
                AbsSender.class, Update.class);
    }

    ProcessTypeHandleMethodChecker createHandleMethodChecker() {
        return new DefaultHandleMethodChecker(createConditionFactory());
    }

    HandleMethodCheckerConditionFactory createConditionFactory() {
        return new DefaultHandleMethodCheckerConditionFactory();
    }

    public static class TestControllerClass {

        @HandleMessage(value = "/start", scope = MatchScope.EQUALS)
        public void validHandleMethod(AbsSender sender, Update update) {}

        @HandleMessage(value = "/invalid", scope = MatchScope.EQUALS)
        public void invalidHandleMethod(AbsSender sender, Update update, String test) {}

    }

}
