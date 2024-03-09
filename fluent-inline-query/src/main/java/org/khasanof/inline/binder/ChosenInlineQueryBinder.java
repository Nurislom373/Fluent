package org.khasanof.inline.binder;

import org.khasanof.annotation.HandleChosenInlineQuery;
import org.khasanof.feature.binder.UpdateTypeBinder;
import org.khasanof.models.executors.UpdateType;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * @author Nurislom
 * @see org.khasanof.inline.binder
 * @since 3/9/2024 12:32 PM
 */
@Component
public class ChosenInlineQueryBinder implements UpdateTypeBinder {

    @Override
    public UpdateType getUpdateType() {
        return UpdateType.CHOSEN_INLINE_QUERY;
    }

    @Override
    public Class<? extends Annotation> getAnnotation() {
        return HandleChosenInlineQuery.class;
    }
}
