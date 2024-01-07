package org.khasanof.collector.loader;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.collector.loader
 * @since 8/4/2023 9:03 PM
 */
public interface HandlerLoader {

    Map<String, Object> getHandlers();

    <T> Map<String, T> getHandlersOfType(Class<T> type);

    Map<String, Object> getHandlers(Class<? extends Annotation> annotation);

}
