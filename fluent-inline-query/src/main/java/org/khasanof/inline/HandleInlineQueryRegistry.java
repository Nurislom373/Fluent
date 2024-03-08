package org.khasanof.inline;

import org.khasanof.annotation.HandleInlineQuery;
import org.khasanof.collector.method.checker.HandleMethodChecker;
import org.khasanof.executors.appropriate.AppropriateUpdateMethod;
import org.khasanof.executors.appropriate.AppropriateUpdateType;
import org.khasanof.executors.execution.Perform;
import org.khasanof.executors.matcher.GenericMatcher;
import org.khasanof.feature.HandleMethodExtraParam;
import org.khasanof.feature.annotation.AnnotationHandler;
import org.khasanof.feature.annotation.HandlerAnnotationRegistry;
import org.khasanof.feature.binder.UpdateTypeBinder;
import org.khasanof.feature.method.MethodType;
import org.khasanof.inline.appropriate.InlineQueryUpdateTypeMatcher;
import org.khasanof.inline.binder.InlineQueryBinder;
import org.khasanof.inline.checker.InlineHandleMethodChecker;
import org.khasanof.inline.matcher.InlineQueryMatcher;
import org.khasanof.inline.param.InlineQueryHandleMethodExtraParam;
import org.khasanof.inline.perform.InlineQueryPerform;
import org.khasanof.inline.type.InlineMethodType;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof.inline
 * @since 3/3/2024 5:33 PM
 */
@Component
public class HandleInlineQueryRegistry implements HandlerAnnotationRegistry {

    @Override
    public AnnotationHandler getAnnotationHandler() {
        return () -> HandleInlineQuery.class;
    }

    @Override
    public MethodType getMethodType() {
        return InlineMethodType.INLINE_QUERY;
    }

    @Override
    public Class<? extends GenericMatcher> getMatcher() {
        return InlineQueryMatcher.class;
    }

    @Override
    public Class<? extends AppropriateUpdateType> getAppropriateUpdateType() {
        return InlineQueryUpdateTypeMatcher.class;
    }

    @Override
    public Class<? extends AppropriateUpdateMethod> getAppropriateUpdateMethod() {
        return null; // because inline query no sub types
    }

    @Override
    public Class<? extends Perform> getPerform() {
        return InlineQueryPerform.class;
    }

    @Override
    public Class<? extends HandleMethodExtraParam> getMethodExtraParam() {
        return InlineQueryHandleMethodExtraParam.class;
    }

    @Override
    public Class<? extends HandleMethodChecker> getHandleMethodChecker() {
        return InlineHandleMethodChecker.class;
    }

    @Override
    public Class<? extends UpdateTypeBinder> getUpdateTypeBinder() {
        return InlineQueryBinder.class;
    }
}
