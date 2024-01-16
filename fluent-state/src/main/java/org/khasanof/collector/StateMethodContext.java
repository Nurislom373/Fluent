package org.khasanof.collector;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.collector
 * @since 1/16/2024 9:44 PM
 */
public interface StateMethodContext extends GenericMethodContext<Enum, Map.Entry<Method, Object>>, AssembleMethods{
}
