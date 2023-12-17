package org.khasanof.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.khasanof.custom.FluentContext;
import org.khasanof.event.ExecutionMethod;
import org.khasanof.event.exceptionDirector.ExceptionDirectorEvent;
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
public class ExecutionExceptionAspect {

    private final ApplicationEventPublisher publisher;

    public ExecutionExceptionAspect(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Pointcut("execution(* org.khasanof.executors.execution.Execution.run(..))")
    void executionRunMethodPointcut(){}

    @AfterThrowing(value = "executionRunMethodPointcut()", throwing = "ex")
    private void afterThrowing(JoinPoint joinPoint, Throwable ex) throws Throwable {
        FluentContext.updateExecutorBoolean.set(true);
        if (ex.getClass().equals(InvocationTargetException.class)) {
            ExecutionMethod event = MethodUtils.getArg(joinPoint.getArgs(), ExecutionMethod.class);
            Object[] args = event.getInvokerModel().getArgs();
            AbsSender sender = MethodUtils.getArg(args, AbsSender.class);
            Update update = MethodUtils.getArg(args, Update.class);
            publisher.publishEvent(new ExceptionDirectorEvent(this, update, sender, ex.getCause()));
        } else {
            throw ex;
        }
    }

}
