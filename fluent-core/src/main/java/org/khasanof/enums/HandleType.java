package org.khasanof.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Author: Nurislom
 * <br/>
 * Date: 20.06.2023
 * <br/>
 * Time: 18:21
 * <br/>
 * Package: org.khasanof.core.enums
 */
public enum HandleType {

    UNKNOWN, ALL, // special types!
    MY_CHAT_MEMBER,
    MESSAGE,
    CALLBACK,
    STICKER,
    PHOTO,
    DOCUMENT,
    VIDEO,
    INLINE_QUERY,
    VOICE,
    CONTACT,
    VIDEO_NOTE,
    LOCATION,
    MEDIA_GROUP,
    AUDIO,
    ANIMATION,
    CHAT_ACTION,
    VENUE,
    DICE,
    POLL

}
