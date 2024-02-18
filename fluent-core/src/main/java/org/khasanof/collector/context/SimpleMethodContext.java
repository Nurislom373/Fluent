package org.khasanof.collector.context;

import org.khasanof.collector.AssembleMethods;
import org.khasanof.collector.GenericMethodContext;
import org.khasanof.feature.AnnotationHandler;
import org.khasanof.models.invoker.SimpleInvoker;

import java.util.List;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.collector
 * @since 8/19/2023 12:48 PM
 */
public interface SimpleMethodContext extends AssembleMethods, GenericMethodContext<AnnotationHandler, List<SimpleInvoker>> {

    /**
     *
     * @return
     */
    Map<AnnotationHandler, List<SimpleInvoker>> findAll();
}
