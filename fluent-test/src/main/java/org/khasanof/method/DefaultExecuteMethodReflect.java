package org.khasanof.method;

import org.springframework.util.ReflectionUtils;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.method
 * @since 1/9/2024 12:09 AM
 */
public class DefaultExecuteMethodReflect implements ExecuteMethodReflect {

    @Override
    public Set<Method> getMethodWithString() {
        return new HashSet<>() {{
            add(ReflectionUtils.findMethod(AbsSender.class, "executeAsync", BotApiMethod.class));
            add(ReflectionUtils.findMethod(AbsSender.class, "execute", BotApiMethod.class));
        }};
    }

}
