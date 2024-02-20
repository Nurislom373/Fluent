package org.khasanof.collector.method.checker;

import org.junit.jupiter.api.Test;
import org.khasanof.annotation.methods.HandleCallback;
import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.annotation.process.ProcessUpdate;
import org.khasanof.enums.MatchScope;
import org.khasanof.exceptions.InvalidParamsException;
import org.khasanof.factories.method.DefaultMethodCheckConditionFactory;
import org.khasanof.factories.method.DefaultMethodCheckConditionFactoryImpl;
import org.khasanof.mediator.DefaultMethodCheckOperationStrategyMediator;
import org.khasanof.registry.annotation.DefaultFluentAnnotationRegistry;
import org.khasanof.registry.annotation.FluentAnnotationsRegistry;
import org.springframework.util.ReflectionUtils;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Nurislom
 * @see org.khasanof.collector.method.checker
 * @since 12/26/2023 10:58 PM
 */
public class DefaultHandleMethodCheckerTest {

    private final ProcessTypeHandleMethodChecker handleMethodChecker = createHandleMethodChecker();

    @Test
    void firstTestCheckMethodShouldSuccess() {
        Method handleMethod = getValidHandleMethod();
        assertTrue(handleMethodChecker.check(handleMethod));
    }

    @Test
    void testCheckMethodShouldFailed() {
        Method invalidHandleMethod = getInvalidHandleMethod();
        assertThrows(InvalidParamsException.class, () -> handleMethodChecker.check(invalidHandleMethod));
    }

    @Test
    void secondTestCheckMethodShouldSuccess() {
        Method handleMethod = getValidHandleMethodSecond();
        assertTrue(handleMethodChecker.check(handleMethod));
    }

    @Test
    void thirdTestCheckMethodShouldSuccess() {
        Method handleMethod = getValidHandlerMethodThird();
        assertTrue(handleMethodChecker.check(handleMethod));
    }

    @Test
    void fourthTestCheckMethodShouldSuccess() {
        Method handleMethod = getValidHandlerMethodFourth();
        assertTrue(handleMethodChecker.check(handleMethod));
    }

    @Test
    void secondTestCheckMethodShouldFailed() {
        Method invalidHandleMethod = getInvalidValidHandlerMethodSecond();
        assertThrows(InvalidParamsException.class, () -> handleMethodChecker.check(invalidHandleMethod));
    }

    private Method getInvalidHandleMethod() {
        return ReflectionUtils.findMethod(TestController.class, "invalidHandleMethod",
                AbsSender.class, Update.class, String.class);
    }

    private Method getValidHandleMethod() {
        return ReflectionUtils.findMethod(TestController.class, "validHandleMethod", Update.class);
    }

    private Method getValidHandleMethodSecond() {
        return ReflectionUtils.findMethod(TestController.class, "validHandleMethod");
    }

    private Method getInvalidValidHandlerMethodSecond() {
        return ReflectionUtils.findMethod(TestController.class, "handleCallback", Update.class, AbsSender.class);
    }

    private Method getValidHandlerMethodThird() {
        return ReflectionUtils.findMethod(TestController.class, "handleCallback", Update.class);
    }

    private Method getValidHandlerMethodFourth() {
        return ReflectionUtils.findMethod(TestController.class, "handleCallback");
    }

    ProcessTypeHandleMethodChecker createHandleMethodChecker() {
        return new DefaultHandleMethodChecker(fluentAnnotationsRegistry(), createConditionFactory(), new DefaultMethodCheckOperationStrategyMediator());
    }

    DefaultMethodCheckConditionFactory createConditionFactory() {
        return new DefaultMethodCheckConditionFactoryImpl();
    }

    FluentAnnotationsRegistry fluentAnnotationsRegistry() {
        DefaultFluentAnnotationRegistry annotationRegistry = new DefaultFluentAnnotationRegistry();
        annotationRegistry.addAnnotations(org.khasanof.utils.ReflectionUtils.getSubTypesSuperAnnotation(ProcessUpdate.class));
        return annotationRegistry;
    }

    public static class TestController {

        @HandleMessage(value = "/start", scope = MatchScope.EQUALS)
        public void validHandleMethod(Update update) {}

        @HandleMessage(value = "/start-time", scope = MatchScope.EQUALS)
        public void validHandleMethod() {}

        @HandleMessage(value = "/invalid", scope = MatchScope.EQUALS)
        public void invalidHandleMethod(AbsSender sender, Update update, String test) {}

        @HandleCallback(value = {"EN", "RU", "UZ"})
        private void handleCallback(Update update, AbsSender sender) {}

        @HandleCallback(value = {"EN", "RU", "UZ"})
        private void handleCallback(Update update) {}

        @HandleCallback(value = {"EN", "RU", "UZ"})
        private void handleCallback() {}

    }

}
