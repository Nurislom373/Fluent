package org.khasanof.collector;

import org.khasanof.GenericContains;
import org.khasanof.collector.questMethod.SearchMethod;

/**
 * @author Nurislom
 * @see org.khasanof.collector
 * @since 25.06.2023 21:04
 */
@SuppressWarnings({"rawtypes"})
public abstract class AbstractCollector {

    protected final SearchMethod questMethod;
    protected GenericContains genericContains;
    protected AnnotationMethodContext annotationContext;

    public AbstractCollector(SearchMethod questMethod) {
        this.questMethod = questMethod;
    }

    public AbstractCollector(SearchMethod questMethod, AnnotationMethodContext annotationContext) {
        this.questMethod = questMethod;
        this.annotationContext = annotationContext;
    }

    public AbstractCollector(SearchMethod questMethod, GenericContains genericContains, AnnotationMethodContext annotationContext) {
        this.questMethod = questMethod;
        this.genericContains = genericContains;
        this.annotationContext = annotationContext;
    }
}
