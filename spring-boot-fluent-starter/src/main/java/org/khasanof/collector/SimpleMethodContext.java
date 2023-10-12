package org.khasanof.collector;

import org.khasanof.enums.HandleClasses;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.collector
 * @since 8/19/2023 12:48 PM
 */
public interface SimpleMethodContext extends AssembleMethods,
        GenericMethodContext<HandleClasses, Map<Method, Object>>, AnnotationMethodContext<Map<Method, Object>> {
}
