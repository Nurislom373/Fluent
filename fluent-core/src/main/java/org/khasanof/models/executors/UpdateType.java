package org.khasanof.models.executors;

import org.khasanof.enums.HandleType;

/**
 * @author Nurislom
 * @see org.khasanof.models.executors
 * @since 12/24/2023 8:20 PM
 */
public enum UpdateType {

    MESSAGE,
    INLINE_QUERY,
    CHOSEN_INLINE_QUERY,
    CALLBACK,
    EDIT_MESSAGE,
    CHANNEL_POST,
    EDIT_CHANNEL_POST,
    SHIPPING_QUERY,
    PRE_CHECKOUT_QUERY,
    POLL,
    POLL_ANSWER,
    MY_CHAT_MEMBER,
    CHAT_MEMBER,
    CHAT_JOIN_REQUEST;

    public HandleType asHandleType() {
        return switch (this) {
            case MESSAGE -> HandleType.MESSAGE;
            case CHOSEN_INLINE_QUERY -> HandleType.CHOSEN_INLINE_QUERY;
            case CALLBACK -> HandleType.CALLBACK;
            case INLINE_QUERY -> HandleType.INLINE_QUERY;
            case EDIT_MESSAGE -> HandleType.EDIT_MESSAGE;
            case CHANNEL_POST -> HandleType.CHANNEL_POST;
            case EDIT_CHANNEL_POST -> HandleType.EDIT_CHANNEL_POST;
            case SHIPPING_QUERY -> HandleType.SHIPPING_QUERY;
            case PRE_CHECKOUT_QUERY -> HandleType.PRE_CHECKOUT_QUERY;
            case POLL -> HandleType.POLL;
            case POLL_ANSWER -> HandleType.POLL_ANSWER;
            case MY_CHAT_MEMBER -> HandleType.MY_CHAT_MEMBER;
            case CHAT_MEMBER -> HandleType.CHAT_MEMBER;
            case CHAT_JOIN_REQUEST -> HandleType.CHAT_JOIN_REQUEST;
        };
    }
}
