package org.khasanof.models.executors;

import org.khasanof.enums.HandleType;

/**
 * @author Nurislom
 * @see org.khasanof.models.executors
 * @since 12/24/2023 8:20 PM
 */
public enum UpdateType {

    MESSAGE, CALLBACK, MY_CHAT_MEMBER;

    public HandleType asHandleType() {
        return switch (this) {
            case MESSAGE -> HandleType.MESSAGE;
            case CALLBACK -> HandleType.CALLBACK;
            case MY_CHAT_MEMBER -> HandleType.MY_CHAT_MEMBER;
        };
    }

}
