package org.khasanof.execute;

import org.telegram.telegrambots.meta.bots.AbsSender;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/8/2024 10:03 PM
 */
public class DefaultExecuteMethodDataAdapter {

    private final Set<String> ignoreMethodNames = Set.of("toString", "equals", "hashCode");
    private final ExecuteMethodCollector executeMethodCollector;

    public DefaultExecuteMethodDataAdapter(ExecuteMethodCollector executeMethodCollector) {
        this.executeMethodCollector = executeMethodCollector;
    }

    public void addDefaultMethods() {
        Arrays.stream(AbsSender.class.getMethods())
                .filter(method -> !Modifier.isFinal(method.getModifiers()))
                .filter(method -> !ignoreMethodNames.contains(method.getName()))
                .forEach(executeMethodCollector::addMethod);
    }

}
