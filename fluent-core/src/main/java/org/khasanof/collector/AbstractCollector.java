package org.khasanof.collector;

import org.khasanof.collector.questMethod.QuestMethod;

/**
 * @author Nurislom
 * @see org.khasanof.collector
 * @since 25.06.2023 21:04
 */
public abstract class AbstractCollector {

    protected final QuestMethod questMethod;
    protected AnnotationMethodContext annotationContext;

    public AbstractCollector(QuestMethod questMethod) {
        this.questMethod = questMethod;
    }

    public AbstractCollector(QuestMethod questMethod, AnnotationMethodContext annotationContext) {
        this.questMethod = questMethod;
        this.annotationContext = annotationContext;
    }
}
