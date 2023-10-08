package org.khasanof.collector.questMethod;

import org.khasanof.enums.HandleType;
import org.khasanof.model.InvokerResult;

import java.util.Set;

/**
 * @author <a href="https://github.com/Nurislom373">Nurislom</a>
 * @see org.khasanof.collector.questMethod
 * @since 23.06.2023 23:43
 */
public interface QuestMethod<P> {

    InvokerResult getMethodValueAnn(Object value, P param);

    default boolean containsKey(P param) {
        return false;
    }

    default InvokerResult getHandleAnyMethod(HandleType handleType) {
        return null;
    }

    default Set<InvokerResult> getAllHandleAnyMethod(HandleType handleType) {
        return null;
    }

}
