package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleAudio;
import org.khasanof.enums.scopes.AudioScope;
import org.khasanof.models.matcher.MatcherParameters;
import org.khasanof.models.matcher.MatcherProperty;
import org.khasanof.models.matcher.function.PropertyFunction;
import org.khasanof.service.expression.ExpressionMatcherService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Audio;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 09.07.2023 16:04
 */
@Component
public class SimpleAudioMatcher extends GenericMatcher<HandleAudio, Message> {

    private final Set<MatcherProperty> matcherProperties = new HashSet<>();

    public SimpleAudioMatcher(ExpressionMatcherService expressionMatcherService) {
        super(expressionMatcherService);
        fillMatcherProperties();
    }

    @Override
    public boolean matcher(HandleAudio annotation, Message value) {
        return expressionMatcherService.match(createParameters(annotation, value));
    }

    private MatcherParameters createParameters(HandleAudio annotation, Message value) {
        return new MatcherParameters(value, annotation.property(), annotation.match(), annotation.value(), matcherProperties);
    }

    void fillMatcherProperties() {
        matcherProperties.add(new MatcherProperty(AudioScope.TITLE, audioPropertyFunction(Audio::getTitle)));
        matcherProperties.add(new MatcherProperty(AudioScope.DURATION, audioPropertyFunction(Audio::getDuration)));
        matcherProperties.add(new MatcherProperty(AudioScope.FILE_NAME, audioPropertyFunction(Audio::getFileName)));
        matcherProperties.add(new MatcherProperty(AudioScope.FILE_SIZE, audioPropertyFunction(Audio::getFileSize)));
        matcherProperties.add(new MatcherProperty(AudioScope.MIME_TYPE, audioPropertyFunction(Audio::getMimeType)));
        matcherProperties.add(new MatcherProperty(AudioScope.PERFORMER, audioPropertyFunction(Audio::getPerformer)));
        matcherProperties.add(new MatcherProperty(AudioScope.CAPTION, (PropertyFunction<Message>) Message::getCaption));
    }

    private PropertyFunction<Message> audioPropertyFunction(Function<Audio, Object> function) {
        return message -> function.apply(message.getAudio());
    }
}
