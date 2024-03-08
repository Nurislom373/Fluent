package org.khasanof.feature.annotation;

import org.khasanof.collector.method.checker.HandleMethodChecker;
import org.khasanof.executors.appropriate.AppropriateUpdateMethod;
import org.khasanof.executors.appropriate.AppropriateUpdateType;
import org.khasanof.executors.execution.Perform;
import org.khasanof.executors.matcher.GenericMatcher;
import org.khasanof.feature.HandleMethodExtraParam;
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
    AnnotationHandler getAnnotationHandler();

    /**
     * @return
     */
    MethodType getMethodType();

    /**
     *
     * @return
     */
    Class<? extends GenericMatcher> getMatcher();

    /**
     * @return
     */
    Class<? extends AppropriateUpdateType> getAppropriateUpdateType();

    /**
     * @return
     */
    Class<? extends AppropriateUpdateMethod> getAppropriateUpdateMethod();

    /**
     * @return
     */
    default Class<?> getAnnotation() {
        return getAnnotationHandler().getAnnotation();
    }

    /**
     * @return
     */
    default Class<? extends Perform> getPerform() {
        return null;
    }

    /**
     *
     * @return
     */
    default Class<? extends HandleMethodExtraParam> getMethodExtraParam() {
        return null;
    }

    /**
     *
     * @return
     */
    default Class<? extends HandleMethodChecker> getHandleMethodChecker() {
        return null;
    }

    /**
     *
     * @return
     */
    default Class<? extends UpdateTypeBinder> getUpdateTypeBinder() {
        return null;
    }
}
