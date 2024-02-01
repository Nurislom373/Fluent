package org.khasanof.mediator;

import org.khasanof.models.invoker.SimpleInvoker;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Nurislom
 * @see org.khasanof.mediator
 * @since 2/1/2024 11:35 PM
 */
public interface PerformMediator {

    void execute(SimpleInvoker simpleInvoker) throws InvocationTargetException, IllegalAccessException;

}
