package org.khasanof.execute;

import org.telegram.telegrambots.meta.bots.AbsSender;

import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/8/2024 10:03 PM
 */
public class DefaultExecuteMethodDataAdapter {

    private final ExecuteMethodCollector executeMethodCollector;

    public DefaultExecuteMethodDataAdapter(ExecuteMethodCollector executeMethodCollector) {
        this.executeMethodCollector = executeMethodCollector;
    }

    public void addDefaultMethods() {
        Arrays.stream(AbsSender.class.getMethods())
                .filter(method -> !Modifier.isFinal(method.getModifiers()))
                .forEach(executeMethodCollector::addMethod);
    }

}
