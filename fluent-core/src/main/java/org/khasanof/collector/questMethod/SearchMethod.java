package org.khasanof.collector.questMethod;

import org.khasanof.enums.HandleType;
import org.khasanof.model.invoker.SimpleInvoker;

import java.util.Set;

/**
 * @author <a href="https://github.com/Nurislom373">Nurislom</a>
 * @see org.khasanof.collector.questMethod
 * @since 23.06.2023 23:43
 */
public interface SearchMethod<P> {

    SimpleInvoker getMethodValueAnn(Object value, P param);

    default boolean containsKey(P param) {
        return false;
    }

    default SimpleInvoker getHandleAnyMethod(HandleType handleType) {
        return null;
    }

    default Set<SimpleInvoker> getAllHandleAnyMethod(HandleType handleType) {
        return null;
    }

}
