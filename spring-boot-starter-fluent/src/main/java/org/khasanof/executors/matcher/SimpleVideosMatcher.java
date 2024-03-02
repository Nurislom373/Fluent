package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleVideo;
import org.khasanof.annotation.methods.HandleVideos;
import org.khasanof.models.matcher.RepeatableMatcherParameters;
import org.khasanof.service.expression.MultiExpressionMatcherService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 06.07.2023 23:42
 */
@Component
public class SimpleVideosMatcher extends MultiGenericMatcher<HandleVideos, HandleVideo, Message> {

    public SimpleVideosMatcher(GenericMatcher<HandleVideo, Message> matcher, MultiExpressionMatcherService expressionMatcherService) {
        super(matcher, expressionMatcherService);
    }

    @Override
    public boolean matcher(HandleVideos annotation, Message value) {
        return expressionMatcherService.match(new RepeatableMatcherParameters(annotation.match(), annotation.value(), matcher, value));
    }
}
