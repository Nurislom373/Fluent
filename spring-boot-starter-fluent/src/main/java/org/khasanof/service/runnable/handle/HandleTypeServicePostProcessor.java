package org.khasanof.service.runnable.handle;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.enums.HandleType;
import org.khasanof.registry.handle.HandleTypeFindRegistry;
import org.khasanof.models.handle.HandleTypeFind;
import org.khasanof.service.runnable.PostProcessor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.service.runnable.handle
 * @since 2/18/2024 5:12 PM
 */
@Slf4j
@Service(HandleTypeServicePostProcessor.NAME)
public class HandleTypeServicePostProcessor implements PostProcessor {

    public static final String NAME = "handleTypeServicePostProcessor";
    private final HandleTypeFindRegistry handleTypeFindRegistry;

    public HandleTypeServicePostProcessor(HandleTypeFindRegistry handleTypeFindRegistry) {
        this.handleTypeFindRegistry = handleTypeFindRegistry;
    }

    @Override
    public void run() {
        log.debug("registering default handle types...");
        // message
        handleTypeFindRegistry.addHandleTypeFind(new HandleTypeFind(HandleType.MESSAGE, true,
                update -> Objects.equals(update.hasMessage(), Boolean.TRUE), messageSubServices()));
        // callback
        handleTypeFindRegistry.addHandleTypeFind(new HandleTypeFind(HandleType.CALLBACK, false,
                update -> Objects.equals(update.hasCallbackQuery(), Boolean.TRUE), Collections.emptyList()));
        // inline query
        handleTypeFindRegistry.addHandleTypeFind(new HandleTypeFind(HandleType.INLINE_QUERY, false,
                update -> Objects.equals(update.hasInlineQuery(), Boolean.TRUE), Collections.emptyList()));
        // my chat member
        handleTypeFindRegistry.addHandleTypeFind(new HandleTypeFind(HandleType.MY_CHAT_MEMBER, false,
                update -> Objects.equals(update.hasMyChatMember(), Boolean.TRUE), Collections.emptyList()));
        // channel post
        handleTypeFindRegistry.addHandleTypeFind(new HandleTypeFind(HandleType.CHANNEL_POST, false,
                update -> Objects.equals(update.hasChannelPost(), Boolean.TRUE), Collections.emptyList()));
        // edit message
        handleTypeFindRegistry.addHandleTypeFind(new HandleTypeFind(HandleType.EDIT_MESSAGE, false,
                update -> Objects.equals(update.hasEditedMessage(), Boolean.TRUE), Collections.emptyList()));
        // edit channel post
        handleTypeFindRegistry.addHandleTypeFind(new HandleTypeFind(HandleType.EDIT_CHANNEL_POST, false,
                update -> Objects.equals(update.hasEditedChannelPost(), Boolean.TRUE), Collections.emptyList()));
    }

    private List<HandleTypeFind> messageSubServices() {
        return List.of(
                // text
                new HandleTypeFind(HandleType.TEXT, false,
                        update -> Objects.equals(update.getMessage().hasText(), Boolean.TRUE), Collections.emptyList()),
                // photo
                new HandleTypeFind(HandleType.PHOTO, false,
                        update -> Objects.equals(update.getMessage().hasPhoto(), Boolean.TRUE), Collections.emptyList()),
                // document
                new HandleTypeFind(HandleType.DOCUMENT, false,
                        update -> Objects.equals(update.getMessage().hasDocument(), Boolean.TRUE), Collections.emptyList()),
                // document
                new HandleTypeFind(HandleType.VIDEO, false,
                        update -> Objects.equals(update.getMessage().hasVideo(), Boolean.TRUE), Collections.emptyList())
        );
    }
}
