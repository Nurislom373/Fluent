package org.khasanof.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.khasanof.annotation.exception.HandleException;
import org.khasanof.annotation.methods.*;
import org.khasanof.feature.AnnotationHandler;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Objects;
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
public enum HandleAnnotation implements AnnotationHandler {

    UNKNOWN(Annotation.class, false) {
        @Override
        public Class<? extends Annotation> getAnnotation() {
            return Annotation.class;
        }
    },
    HANDLE_ANY(HandleAny.class, false) {
        @Override
        public Class<? extends Annotation> getAnnotation() {
            return HandleAny.class;
        }
    },
    HANDLE_EXCEPTION(HandleException.class, false) {
        @Override
        public Class<? extends Annotation> getAnnotation() {
            return HandleException.class;
        }
    },

    //

    HANDLE_AUDIOS(HandleAudios.class, false) {
        @Override
        public Class<? extends Annotation> getAnnotation() {
            return HandleAudios.class;
        }
    },
    HANDLE_VIDEOS(HandleVideos.class, false) {
        @Override
        public Class<? extends Annotation> getAnnotation() {
            return HandleVideos.class;
        }
    },
    HANDLE_PHOTOS(HandlePhotos.class, false) {
        @Override
        public Class<? extends Annotation> getAnnotation() {
            return HandlePhotos.class;
        }
    },
    HANDLE_CALLBACKS(HandleCallbacks.class, false) {
        @Override
        public Class<? extends Annotation> getAnnotation() {
            return HandleCallbacks.class;
        }
    },
    HANDLE_MESSAGES(HandleMessages.class, false) {
        @Override
        public Class<? extends Annotation> getAnnotation() {
            return HandleMessages.class;
        }
    },
    HANDLE_DOCUMENTS(HandleDocuments.class, false) {
        @Override
        public Class<? extends Annotation> getAnnotation() {
            return HandleDocuments.class;
        }
    },

    //

    HANDLE_AUDIO(HandleAudio.class, true, HANDLE_AUDIOS) {
        @Override
        public Class<? extends Annotation> getAnnotation() {
            return HandleAudio.class;
        }
        @Override
        public boolean isRepeatable() {
            return true;
        }

        @Override
        public AnnotationHandler repeatableAnnotationHandler() {
            return HANDLE_AUDIOS;
        }
    },

    HANDLE_VIDEO(HandleVideo.class, true, HANDLE_VIDEOS) {
        @Override
        public Class<? extends Annotation> getAnnotation() {
            return HandleVideo.class;
        }
        @Override
        public boolean isRepeatable() {
            return true;
        }
        @Override
        public AnnotationHandler repeatableAnnotationHandler() {
            return HANDLE_VIDEOS;
        }
    },
    HANDLE_PHOTO(HandlePhoto.class, true, HANDLE_PHOTOS) {
        @Override
        public Class<? extends Annotation> getAnnotation() {
            return HandlePhoto.class;
        }
        @Override
        public boolean isRepeatable() {
            return true;
        }
        @Override
        public AnnotationHandler repeatableAnnotationHandler() {
            return HANDLE_PHOTOS;
        }
    },
    HANDLE_CALLBACK(HandleCallback.class, true, HANDLE_CALLBACKS) {
        @Override
        public Class<? extends Annotation> getAnnotation() {
            return HandleCallback.class;
        }
        @Override
        public boolean isRepeatable() {
            return true;
        }
        @Override
        public AnnotationHandler repeatableAnnotationHandler() {
            return HANDLE_CALLBACKS;
        }
    },
    HANDLE_MESSAGE(HandleMessage.class, true, HANDLE_MESSAGES) {
        @Override
        public Class<? extends Annotation> getAnnotation() {
            return HandleMessage.class;
        }
        @Override
        public boolean isRepeatable() {
            return true;
        }
        @Override
        public AnnotationHandler repeatableAnnotationHandler() {
            return HANDLE_MESSAGES;
        }
    },
    HANDLE_DOCUMENT(HandleDocument.class, true, HANDLE_DOCUMENTS) {
        @Override
        public Class<? extends Annotation> getAnnotation() {
            return HandleDocument.class;
        }
        @Override
        public boolean isRepeatable() {
            return true;
        }
        @Override
        public AnnotationHandler repeatableAnnotationHandler() {
            return HANDLE_DOCUMENTS;
        }
    };

    private final Class<? extends Annotation> type;
    private final boolean isMultiVersion;
    private HandleAnnotation subHandleClasses;
    private boolean isSuperAnnotation;

    HandleAnnotation(Class<? extends Annotation> type, boolean hasSubType, HandleAnnotation subHandleClasses) {
        this.type = type;
        this.isMultiVersion = hasSubType;
        this.subHandleClasses = subHandleClasses;
    }

    HandleAnnotation(Class<? extends Annotation> type, boolean hasSubType, boolean isSuperAnnotation) {
        this.type = type;
        this.isMultiVersion = hasSubType;
        this.isSuperAnnotation = isSuperAnnotation;
    }

    public static Set<Class<? extends Annotation>> getAllAnnotations() {
        return Arrays.stream(values())
                .map(an -> an.type)
                .collect(Collectors.toSet());
    }

    public static HandleAnnotation getHandleWithType(Class<? extends Annotation> annotation) {
        return Arrays.stream(values())
                .filter(handle -> Objects.equals(annotation, handle.getType()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Match type not found!"));
    }

    private static boolean hasSuperAnnotation(Class<? extends Annotation> superAnn, Class<? extends Annotation> ann) {
        return Arrays.stream(ann.getDeclaredAnnotations())
                .anyMatch(annotation -> annotation.annotationType().equals(superAnn));
    }
}
