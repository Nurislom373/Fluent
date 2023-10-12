package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleAudio;
import org.khasanof.annotation.methods.HandleAudios;
import org.khasanof.config.ApplicationConstants;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Audio;

import java.util.Arrays;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 09.07.2023 16:08
 */
@Component
public class SimpleAudiosMatcher extends MultiGenericMatcher<HandleAudios, HandleAudio, Audio> {

    public SimpleAudiosMatcher(SimpleAudioMatcher matcher) {
        super(matcher, ApplicationConstants.MATCHER_MAP);
    }

    @Override
    public boolean matcher(HandleAudios annotation, Audio value) {
        return multiMatchScopeFunctionMap.get(annotation.match())
                .apply(Arrays.stream(annotation.values()),
                        (handleAudio -> matcher.matcher(handleAudio, value)));
    }

    @Override
    public Class<HandleAudios> getType() {
        return HandleAudios.class;
    }
}
