package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleVideo;
import org.khasanof.config.ApplicationConstants;
import org.khasanof.enums.scopes.VideoScope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Video;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 06.07.2023 23:34
 */
@Component
public class SimpleVideoMatcher extends GenericMatcher<HandleVideo, Video> {

    private final Map<VideoScope, Function<Video, Object>> biFunctionMap = new HashMap<>();

    {
        setBiFunctionMap();
    }

    public SimpleVideoMatcher() {
        super(ApplicationConstants.MATCHER_MAP);
    }

    @Override
    public boolean matcher(HandleVideo annotation, Video value) {
        Object apply = biFunctionMap.get(annotation.scope()).apply(value);
        return matchFunctions.get(Map.entry(annotation.match(), getMatchType(apply, annotation.match())))
                .apply(annotation.value(), apply);
    }

    @Override
    public Class<HandleVideo> getType() {
        return HandleVideo.class;
    }

    void setBiFunctionMap() {
        biFunctionMap.put(VideoScope.HEIGHT, Video::getHeight);
        biFunctionMap.put(VideoScope.WIDTH, Video::getWidth);
        biFunctionMap.put(VideoScope.DURATION, Video::getDuration);
        biFunctionMap.put(VideoScope.FILE_NAME, Video::getFileName);
        biFunctionMap.put(VideoScope.MIME_TYPE, Video::getMimeType);
        biFunctionMap.put(VideoScope.FILE_SIZE, Video::getFileSize);
    }
}
