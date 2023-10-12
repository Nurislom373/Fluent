package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleVideoNote;
import org.khasanof.config.ApplicationConstants;
import org.khasanof.enums.scopes.VideoNoteScope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.VideoNote;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 09.07.2023 16:47
 */
@Component
public class SimpleVideoNoteMatcher extends GenericMatcher<HandleVideoNote, VideoNote> {

    private final Map<VideoNoteScope, Function<VideoNote, Object>> biFunctionMap = new HashMap<>();

    {
        setBiFunctionMap();
    }

    public SimpleVideoNoteMatcher() {
        super(ApplicationConstants.MATCHER_MAP);
    }

    @Override
    public boolean matcher(HandleVideoNote annotation, VideoNote value) {
        Object apply = biFunctionMap.get(annotation.scope()).apply(value);
        return matchFunctions.get(Map.entry(annotation.match(), getScopeType(apply, annotation.match())))
                .apply(annotation.value(), apply);
    }

    @Override
    public Class<HandleVideoNote> getType() {
        return HandleVideoNote.class;
    }

    void setBiFunctionMap() {
        biFunctionMap.put(VideoNoteScope.DURATION, VideoNote::getDuration);
        biFunctionMap.put(VideoNoteScope.FILE_SIZE, VideoNote::getFileSize);
    }
}
