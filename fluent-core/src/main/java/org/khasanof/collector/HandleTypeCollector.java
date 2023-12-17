package org.khasanof.collector;

import org.khasanof.enums.HandleType;
import org.khasanof.models.invoker.SimpleInvoker;

import java.util.Optional;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.collector
 * @since 8/19/2023 3:06 PM
 */
public interface HandleTypeCollector {

    default Optional<SimpleInvoker> getHandleAnyMethod(HandleType handleType) {
        throw new RuntimeException("Not implemented!");
    }

    default Set<SimpleInvoker> getAllHandleAnyMethod(HandleType handleType) {
        throw new RuntimeException("Not implemented!");
    }

}
