package org.khasanof.collector.questMethod;

import org.khasanof.model.InvokerMethod;
import org.khasanof.model.InvokerResult;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.collector.questMethod
 * @since 12/9/2023 8:14 PM
 */
public abstract class SearchMethodUtils {

    public static InvokerResult resultCreator(Map.Entry<Method, Object> entry) {
        return Objects.nonNull(entry) ? new InvokerMethod(entry.getKey(), entry.getValue()) : null;
    }

}
