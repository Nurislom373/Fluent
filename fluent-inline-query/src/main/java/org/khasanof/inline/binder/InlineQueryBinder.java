package org.khasanof.inline.binder;

import org.khasanof.annotation.HandleInlineQuery;
import org.khasanof.feature.binder.UpdateTypeBinder;
import org.khasanof.models.executors.UpdateType;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * @author Nurislom
 * @see org.khasanof.inline.binder
 * @since 3/3/2024 5:45 PM
 */
@Component
public class InlineQueryBinder implements UpdateTypeBinder {

    @Override
    public UpdateType getUpdateType() {
        return UpdateType.INLINE_QUERY;
    }

    @Override
    public Class<? extends Annotation> getAnnotation() {
        return HandleInlineQuery.class;
    }
}
