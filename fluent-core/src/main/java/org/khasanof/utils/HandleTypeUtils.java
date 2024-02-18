package org.khasanof.utils;

import org.khasanof.enums.HandleAnnotation;
import org.khasanof.enums.HandleType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.utils
 * @since 1/6/2024 8:58 PM
 */
public abstract class HandleTypeUtils {

    private static final List<HandleType> HANDLE_TYPES = new ArrayList<>();

    static {
        HANDLE_TYPES.add(HandleType.MESSAGE);
        HANDLE_TYPES.add(HandleType.CALLBACK);
        HANDLE_TYPES.add(HandleType.PHOTO);
        HANDLE_TYPES.add(HandleType.DOCUMENT);
        HANDLE_TYPES.add(HandleType.VIDEO);
        HANDLE_TYPES.add(HandleType.INLINE_QUERY);
        HANDLE_TYPES.add(HandleType.AUDIO);
    }

    public static boolean isConvertHandleClass(HandleType handleType) {
        return HANDLE_TYPES.contains(handleType);
    }

    public static HandleAnnotation handleTypeToClass(HandleType type) {
        return switch (type) {
            case MESSAGE -> HandleAnnotation.HANDLE_MESSAGE;
            case CALLBACK -> HandleAnnotation.HANDLE_CALLBACK;
            case PHOTO -> HandleAnnotation.HANDLE_PHOTO;
            case DOCUMENT -> HandleAnnotation.HANDLE_DOCUMENT;
            case VIDEO -> HandleAnnotation.HANDLE_VIDEO;
            case AUDIO -> HandleAnnotation.HANDLE_AUDIO;
            default -> HandleAnnotation.UNKNOWN;
        };
    }

    public static HandleType handleClassToType(HandleAnnotation clazz) {
        return switch (clazz) {
            case HANDLE_AUDIOS, HANDLE_AUDIO -> HandleType.AUDIO;
            case HANDLE_VIDEOS, HANDLE_VIDEO -> HandleType.VIDEO;
            case HANDLE_PHOTOS, HANDLE_PHOTO -> HandleType.PHOTO;
            case HANDLE_CALLBACKS, HANDLE_CALLBACK -> HandleType.CALLBACK;
            case HANDLE_MESSAGES, HANDLE_MESSAGE -> HandleType.MESSAGE;
            case HANDLE_DOCUMENTS, HANDLE_DOCUMENT -> HandleType.DOCUMENT;
            default -> HandleType.UNKNOWN;
        };
    }

}
