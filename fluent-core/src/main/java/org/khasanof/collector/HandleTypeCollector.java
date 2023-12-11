package org.khasanof.collector;

import org.khasanof.enums.HandleType;
import org.khasanof.model.invoker.SimpleInvoker;

import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.collector
 * @since 8/19/2023 3:06 PM
 */
public interface HandleTypeCollector {

    default SimpleInvoker getHandleAnyMethod(HandleType handleType) {
        throw new RuntimeException("Not implemented!");
    }

    default Set<SimpleInvoker> getAllHandleAnyMethod(HandleType handleType) {
        throw new RuntimeException("Not implemented!");
    }

}
