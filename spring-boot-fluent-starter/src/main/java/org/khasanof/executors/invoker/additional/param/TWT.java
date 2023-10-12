package org.khasanof.executors.invoker.additional.param;

import org.khasanof.model.AdditionalType;
import org.khasanof.model.InvokerModelV2;

import java.lang.reflect.Method;

/**
 * Take With Type Interface
 *
 * @author Nurislom
 * @see org.khasanof.executors.invoker.additional
 * @since 8/13/2023 7:04 PM
 */
public interface TWT extends AdditionalType {

    Object getValue(InvokerModelV2 invokerModel, Object[] args, Method method);

}
