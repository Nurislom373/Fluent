package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleVideo;
import org.khasanof.annotation.methods.HandleVideos;
import org.khasanof.config.ApplicationConstants;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Video;

import java.util.Arrays;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 06.07.2023 23:42
 */
@Component
public class SimpleVideosMatcher extends MultiGenericMatcher<HandleVideos, HandleVideo, Video> {

    public SimpleVideosMatcher(GenericMatcher<HandleVideo, Video> matcher) {
        super(matcher, ApplicationConstants.MATCHER_MAP);
    }

    @Override
    public boolean matcher(HandleVideos annotation, Video value) {
        return multiMatchScopeFunctionMap.get(annotation.match())
                .apply(Arrays.stream(annotation.values()),
                        (handleVideo -> matcher.matcher(handleVideo, value)));
    }

    @Override
    public Class<HandleVideos> getType() {
        return HandleVideos.class;
    }

}
