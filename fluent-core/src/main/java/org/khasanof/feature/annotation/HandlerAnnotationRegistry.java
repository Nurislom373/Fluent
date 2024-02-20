package org.khasanof.feature.annotation;

import org.khasanof.collector.method.checker.HandleMethodChecker;
import org.khasanof.executors.appropriate.AppropriateUpdateMethod;
import org.khasanof.executors.appropriate.AppropriateUpdateType;
import org.khasanof.executors.execution.Perform;
import org.khasanof.executors.matcher.GenericMatcher;
import org.khasanof.feature.binder.UpdateTypeBinder;
import org.khasanof.feature.method.MethodType;

/**
 * @author Nurislom
 * @see org.khasanof.feature.annotation
 * @since 2/19/2024 9:16 PM
 */
public interface HandlerAnnotationRegistry {

    /**
     * @return
     */
    Class<?> getAnnotation();

    /**
     * @return
     */
    AnnotationHandler getAnnotationHandler();

    /**
     * @return
     */
    MethodType getMethodType();

    /**
     *
     * @return
     */
    GenericMatcher getMatcher();

    /**
     * @return
     */
    default AppropriateUpdateType getAppropriateUpdateType() {
        return null;
    }

    /**
     * @return
     */
    default AppropriateUpdateMethod getAppropriateUpdateMethod() {
        return null;
    }

    /**
     * @return
     */
    default Perform getPerform() {
        return null;
    }

    /**
     *
     * @return
     */
    default HandleMethodChecker getHandleMethodChecker() {
        return null;
    }

    /**
     *
     * @return
     */
    default UpdateTypeBinder getUpdateTypeBinder() {
        return null;
    }
}
