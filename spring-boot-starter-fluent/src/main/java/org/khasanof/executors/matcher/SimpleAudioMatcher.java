package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleAudio;
import org.khasanof.config.ApplicationConstants;
import org.khasanof.enums.scopes.AudioScope;
import org.khasanof.models.matcher.MatcherProperty;
import org.khasanof.models.matcher.PropertyFunction;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Audio;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 09.07.2023 16:04
 */
@Component
public class SimpleAudioMatcher extends GenericMatcher<HandleAudio, Audio> {

    private final Map<AudioScope, Function<Audio, Object>> biFunctionMap = new HashMap<>();

    {
        setBiFunctionMap();
    }

    public SimpleAudioMatcher() {
        super(ApplicationConstants.MATCHER_MAP);
    }

    @Override
    public boolean matcher(HandleAudio annotation, Audio value) {
        Object apply = biFunctionMap.get(annotation.scope()).apply(value);
        return matchFunctions.get(Map.entry(annotation.match(), getMatchType(apply, annotation.match())))
                .apply(annotation.value(), apply);
    }

    void setBiFunctionMap() {
        biFunctionMap.put(AudioScope.FILE_NAME, Audio::getFileName);
        biFunctionMap.put(AudioScope.FILE_SIZE, Audio::getFileSize);
        biFunctionMap.put(AudioScope.TITLE, Audio::getTitle);
        biFunctionMap.put(AudioScope.PERFORMER, Audio::getPerformer);
        biFunctionMap.put(AudioScope.DURATION, Audio::getDuration);
        biFunctionMap.put(AudioScope.MIME_TYPE, Audio::getMimeType);

        MatcherProperty matcherProperty = new MatcherProperty();
        matcherProperty.setProperty(AudioScope.FILE_NAME);
        matcherProperty.setFunction((PropertyFunction<Audio>) Audio::getFileName);
    }
}
