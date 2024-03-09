package org.khasanof.inline;

import org.khasanof.annotation.HandleChosenInlineQuery;
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
import org.khasanof.inline.appropriate.ChosenInlineQueryUpdateTypeMatcher;
import org.khasanof.inline.binder.ChosenInlineQueryBinder;
import org.khasanof.inline.checker.ChosenInlineHandleMethodChecker;
import org.khasanof.inline.matcher.ChosenInlineQueryMatcher;
import org.khasanof.inline.param.ChosenInlineQueryHandleMethodExtraParam;
import org.khasanof.inline.perform.ChosenInlineQueryPerform;
import org.khasanof.inline.type.InlineMethodType;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof.inline
 * @since 3/9/2024 1:09 PM
 */
@Component
public class HandleChosenInlineQueryRegistry implements HandlerAnnotationRegistry {

    @Override
    public AnnotationHandler getAnnotationHandler() {
        return () -> HandleChosenInlineQuery.class;
    }

    @Override
    public MethodType getMethodType() {
        return InlineMethodType.CHOSEN_INLINE_QUERY;
    }

    @Override
    public Class<? extends GenericMatcher> getMatcher() {
        return ChosenInlineQueryMatcher.class;
    }

    @Override
    public Class<? extends AppropriateUpdateType> getAppropriateUpdateType() {
        return ChosenInlineQueryUpdateTypeMatcher.class;
    }

    @Override
    public Class<? extends AppropriateUpdateMethod> getAppropriateUpdateMethod() {
        return null; // because chosen inline query no sub types
    }

    @Override
    public Class<? extends Perform> getPerform() {
        return ChosenInlineQueryPerform.class;
    }

    @Override
    public Class<? extends HandleMethodExtraParam> getMethodExtraParam() {
        return ChosenInlineQueryHandleMethodExtraParam.class;
    }

    @Override
    public Class<? extends HandleMethodChecker> getHandleMethodChecker() {
        return ChosenInlineHandleMethodChecker.class;
    }

    @Override
    public Class<? extends UpdateTypeBinder> getUpdateTypeBinder() {
        return ChosenInlineQueryBinder.class;
    }
}
