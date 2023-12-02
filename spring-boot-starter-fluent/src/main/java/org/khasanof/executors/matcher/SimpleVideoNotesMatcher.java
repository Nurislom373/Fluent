package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleVideoNote;
import org.khasanof.annotation.methods.HandleVideoNotes;
import org.khasanof.config.ApplicationConstants;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.VideoNote;

import java.util.Arrays;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 09.07.2023 16:54
 */
@Component
public class SimpleVideoNotesMatcher extends MultiGenericMatcher<HandleVideoNotes, HandleVideoNote, VideoNote> {

    public SimpleVideoNotesMatcher(GenericMatcher<HandleVideoNote, VideoNote> matcher) {
        super(matcher, ApplicationConstants.MATCHER_MAP);
    }

    @Override
    public boolean matcher(HandleVideoNotes annotation, VideoNote value) {
        return multiMatchScopeFunctionMap.get(annotation.match())
                .apply(Arrays.stream(annotation.values()),
                        (handleVideo -> matcher.matcher(handleVideo, value)));
    }

    @Override
    public Class<HandleVideoNotes> getType() {
        return HandleVideoNotes.class;
    }
}
