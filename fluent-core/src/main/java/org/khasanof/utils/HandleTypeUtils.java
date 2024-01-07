package org.khasanof.utils;

import org.khasanof.enums.HandleClasses;
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
        HANDLE_TYPES.add(HandleType.MY_CHAT_MEMBER);
        HANDLE_TYPES.add(HandleType.MESSAGE);
        HANDLE_TYPES.add(HandleType.CALLBACK);
        HANDLE_TYPES.add(HandleType.PHOTO);
        HANDLE_TYPES.add(HandleType.DOCUMENT);
        HANDLE_TYPES.add(HandleType.VIDEO);
        HANDLE_TYPES.add(HandleType.INLINE_QUERY);
        HANDLE_TYPES.add(HandleType.VIDEO_NOTE);
        HANDLE_TYPES.add(HandleType.AUDIO);
    }

    public static boolean isConvertHandleClass(HandleType handleType) {
        return HANDLE_TYPES.contains(handleType);
    }

    public static HandleClasses handleTypeToClass(HandleType type) {
        return switch (type) {
            case MY_CHAT_MEMBER -> HandleClasses.HANDLE_MY_CHAT_MEMBER;
            case MESSAGE -> HandleClasses.HANDLE_MESSAGE;
            case CALLBACK -> HandleClasses.HANDLE_CALLBACK;
            case PHOTO -> HandleClasses.HANDLE_PHOTO;
            case DOCUMENT -> HandleClasses.HANDLE_DOCUMENT;
            case VIDEO -> HandleClasses.HANDLE_VIDEO;
            case INLINE_QUERY -> HandleClasses.HANDLE_INLINE_QUERY;
            case VIDEO_NOTE -> HandleClasses.HANDLE_VIDEO_NOTE;
            case AUDIO -> HandleClasses.HANDLE_AUDIO;
            default -> HandleClasses.UNKNOWN;
        };
    }

    public static HandleType handleClassToType(HandleClasses clazz) {
        return switch (clazz) {
            case HANDLE_MY_CHAT_MEMBER -> HandleType.MY_CHAT_MEMBER;
            case HANDLE_INLINE_QUERY -> HandleType.INLINE_QUERY;
            case HANDLE_VIDEO_NOTES, HANDLE_VIDEO_NOTE -> HandleType.VIDEO_NOTE;
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
