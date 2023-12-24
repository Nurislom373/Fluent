package org.khasanof.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.khasanof.annotation.exception.HandleException;
import org.khasanof.annotation.extra.HandleState;
import org.khasanof.annotation.methods.*;
import org.khasanof.annotation.methods.inline.HandleInlineQuery;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Author: Nurislom
 * <br/>
 * Date: 20.06.2023
 * <br/>
 * Time: 22:35
 * <br/>
 * Package: org.khasanof.core.enums
 */
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public enum HandleClasses {

    HANDLE_ANY(HandleAny.class, false),
    HANDLE_INLINE_QUERY(HandleInlineQuery.class, false),
    HANDLE_EXCEPTION(HandleException.class, false),

    HANDLE_VIDEO_NOTES(HandleVideoNotes.class, false),
    HANDLE_AUDIOS(HandleAudios.class, false),
    HANDLE_VIDEOS(HandleVideos.class, false),
    HANDLE_PHOTOS(HandlePhotos.class, false),
    HANDLE_CALLBACKS(HandleCallbacks.class, false),
    HANDLE_MESSAGES(HandleMessages.class, false),
    HANDLE_DOCUMENTS(HandleDocuments.class, false),

    HANDLE_VIDEO_NOTE(HandleVideoNote.class, true, HANDLE_VIDEO_NOTES),
    HANDLE_AUDIO(HandleAudio.class, true, HANDLE_AUDIOS),
    HANDLE_VIDEO(HandleVideo.class, true, HANDLE_VIDEOS),
    HANDLE_PHOTO(HandlePhoto.class, true, HANDLE_PHOTOS),
    HANDLE_CALLBACK(HandleCallback.class, true, HANDLE_CALLBACKS),
    HANDLE_MESSAGE(HandleMessage.class, true, HANDLE_MESSAGES),
    HANDLE_DOCUMENT(HandleDocument.class, true, HANDLE_DOCUMENTS);

    private final Class<? extends Annotation> type;
    private final boolean hasSubType;
    private HandleClasses subHandleClasses;
    private boolean isSuperAnnotation;

    HandleClasses(Class<? extends Annotation> type, boolean hasSubType, HandleClasses subHandleClasses) {
        this.type = type;
        this.hasSubType = hasSubType;
        this.subHandleClasses = subHandleClasses;
    }

    HandleClasses(Class<? extends Annotation> type, boolean hasSubType, boolean isSuperAnnotation) {
        this.type = type;
        this.hasSubType = hasSubType;
        this.isSuperAnnotation = isSuperAnnotation;
    }

    public static Set<Class<? extends Annotation>> getAllAnnotations() {
        return Arrays.stream(values()).map(an -> an.type)
                .collect(Collectors.toSet());
    }

    public static HandleClasses getHandleWithType(Class<? extends Annotation> annotation) {
        return Arrays.stream(values()).filter(handle -> handle.type.equals(annotation))
                .findFirst().orElseThrow(() -> new RuntimeException("Match type not found!"));
    }

    private static boolean hasSuperAnnotation(Class<? extends Annotation> superAnn, Class<? extends Annotation> ann) {
        return Arrays.stream(ann.getDeclaredAnnotations())
                .anyMatch(annotation -> annotation.annotationType().equals(superAnn));
    }

}
