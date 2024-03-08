package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleAudio;
import org.khasanof.annotation.methods.HandleAudios;
import org.khasanof.models.matcher.RepeatableMatcherParameters;
import org.khasanof.service.expression.MultiExpressionMatcherService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 09.07.2023 16:08
 */
@Component
public class SimpleAudiosMatcher extends MultiGenericMatcher<HandleAudios, Message> {

    public SimpleAudiosMatcher(GenericMatcher<HandleAudio, Message> matcher, MultiExpressionMatcherService expressionMatcherService) {
        super(matcher, expressionMatcherService);
    }

    @Override
    public boolean matcher(HandleAudios annotation, Message value) {
        return expressionMatcherService.match(new RepeatableMatcherParameters(annotation.match(), annotation.value(), matcher, value));
    }
}
