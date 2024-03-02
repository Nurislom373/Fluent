package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandlePhoto;
import org.khasanof.annotation.methods.HandlePhotos;
import org.khasanof.models.matcher.RepeatableMatcherParameters;
import org.khasanof.service.expression.MultiExpressionMatcherService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 06.07.2023 22:57
 */
@Component
public class SimplePhotosMatcher extends MultiGenericMatcher<HandlePhotos, HandlePhoto, Message> {

    public SimplePhotosMatcher(GenericMatcher<HandlePhoto, Message> matcher, MultiExpressionMatcherService expressionMatcherService) {
        super(matcher, expressionMatcherService);
    }

    @Override
    public boolean matcher(HandlePhotos annotation, Message value) {
        return expressionMatcherService.match(new RepeatableMatcherParameters(annotation.match(), annotation.value(), matcher, value));
    }
}
