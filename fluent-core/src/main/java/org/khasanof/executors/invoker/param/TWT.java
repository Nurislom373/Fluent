package org.khasanof.executors.invoker.param;

import org.khasanof.models.AdditionalType;
import org.khasanof.models.Invoker;

import java.lang.reflect.Method;

/**
 * Take With Type Interface
 *
 * @author Nurislom
 * @see org.khasanof.executors.invoker
 * @since 8/13/2023 7:04 PM
 */
public interface TWT extends AdditionalType {

    Object getValue(Invoker invokerModel, Object[] args, Method method);

}
