package org.khasanof.models.handler;

import org.khasanof.models.condition.Condition;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.models.handler
 * @since 1/16/2024 9:38 PM
 */
public interface Handler {

    Method getMethod();

    Object getReference();

    List<Condition> getConditions();

}
