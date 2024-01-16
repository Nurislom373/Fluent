package org.khasanof.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.khasanof.context.FluentThreadLocalContext;
import org.khasanof.event.ExecutionMethod;
import org.khasanof.event.exception.ResolveExceptionEvent;
import org.khasanof.utils.MethodUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Nurislom
 * @see org.khasanof.aop
 * @since 8/13/2023 11:11 PM
 */
@Aspect
@Component
public class ExecutionExceptionHandlerAspect {

    private final ApplicationEventPublisher publisher;

    public ExecutionExceptionHandlerAspect(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Pointcut("execution(* org.khasanof.executors.execution.Execution.run(..))")
    void executionRunMethodPointcut(){}

    @AfterThrowing(value = "executionRunMethodPointcut()", throwing = "ex")
    private void handleException(JoinPoint joinPoint, Throwable ex) throws Throwable {
        FluentThreadLocalContext.updateExecutorBoolean.set(true);
        redirectExceptionHandlerOrThrow(joinPoint, ex);
    }

    private void redirectExceptionHandlerOrThrow(JoinPoint joinPoint, Throwable ex) throws Throwable {
        if (ex.getClass().equals(InvocationTargetException.class)) {
            publishExceptionEvent(joinPoint, ex);
        } else {
            throw ex;
        }
    }

    private void publishExceptionEvent(JoinPoint joinPoint, Throwable ex) {
        ExecutionMethod event = MethodUtils.getArg(joinPoint.getArgs(), ExecutionMethod.class);
        Object[] args = event.getInvokerModel().getArgs();
        AbsSender sender = MethodUtils.getArg(args, AbsSender.class);
        Update update = MethodUtils.getArg(args, Update.class);
        publisher.publishEvent(new ResolveExceptionEvent(this, update, sender, ex.getCause()));
    }

}
