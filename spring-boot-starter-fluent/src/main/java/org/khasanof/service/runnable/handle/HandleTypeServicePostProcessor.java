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
        // shipping query
        handleTypeFindRegistry.addHandleTypeFind(new HandleTypeFind(HandleType.SHIPPING_QUERY, false,
                update -> Objects.equals(update.hasShippingQuery(), Boolean.TRUE), Collections.emptyList()));
        // pre checkout query
        handleTypeFindRegistry.addHandleTypeFind(new HandleTypeFind(HandleType.PRE_CHECKOUT_QUERY, false,
                update -> Objects.equals(update.hasPreCheckoutQuery(), Boolean.TRUE), Collections.emptyList()));
        // poll answer
        handleTypeFindRegistry.addHandleTypeFind(new HandleTypeFind(HandleType.POLL_ANSWER, false,
                update -> Objects.equals(update.hasPollAnswer(), Boolean.TRUE), Collections.emptyList()));
        // chat member
        handleTypeFindRegistry.addHandleTypeFind(new HandleTypeFind(HandleType.CHAT_MEMBER, false,
                update -> Objects.equals(update.hasChatMember(), Boolean.TRUE), Collections.emptyList()));
        // chat join request
        handleTypeFindRegistry.addHandleTypeFind(new HandleTypeFind(HandleType.CHAT_JOIN_REQUEST, false,
                update -> Objects.equals(update.hasChatJoinRequest(), Boolean.TRUE), Collections.emptyList()));
        // chosen inline query
        handleTypeFindRegistry.addHandleTypeFind(new HandleTypeFind(HandleType.CHOSEN_INLINE_QUERY, false,
                update -> Objects.equals(update.hasChosenInlineQuery(), Boolean.TRUE), Collections.emptyList()));
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
                // video
                new HandleTypeFind(HandleType.VIDEO, false,
                        update -> Objects.equals(update.getMessage().hasVideo(), Boolean.TRUE), Collections.emptyList()),
                // video note
                new HandleTypeFind(HandleType.VIDEO_NOTE, false,
                        update -> Objects.equals(update.getMessage().hasVideoNote(), Boolean.TRUE), Collections.emptyList()),
                // sticker
                new HandleTypeFind(HandleType.STICKER, false,
                        update -> Objects.equals(update.getMessage().hasSticker(), Boolean.TRUE), Collections.emptyList()),
                // poll
                new HandleTypeFind(HandleType.POLL, false,
                        update -> Objects.equals(update.getMessage().hasPoll(), Boolean.TRUE), Collections.emptyList()),
                // audio
                new HandleTypeFind(HandleType.AUDIO, false,
                        update -> Objects.equals(update.getMessage().hasAudio(), Boolean.TRUE), Collections.emptyList()),
                // animation
                new HandleTypeFind(HandleType.ANIMATION, false,
                        update -> Objects.equals(update.getMessage().hasAnimation(), Boolean.TRUE), Collections.emptyList()),
                // contact
                new HandleTypeFind(HandleType.CONTACT, false,
                        update -> Objects.equals(update.getMessage().hasContact(), Boolean.TRUE), Collections.emptyList()),
                // voice
                new HandleTypeFind(HandleType.VOICE, false,
                        update -> Objects.equals(update.getMessage().hasVoice(), Boolean.TRUE), Collections.emptyList()),
                // location
                new HandleTypeFind(HandleType.LOCATION, false,
                        update -> Objects.equals(update.getMessage().hasLocation(), Boolean.TRUE), Collections.emptyList()),
                // passport data
                new HandleTypeFind(HandleType.PASSPORT_DATA, false,
                        update -> Objects.equals(update.getMessage().hasPassportData(), Boolean.TRUE), Collections.emptyList()),
                // web app data
                new HandleTypeFind(HandleType.WEB_APP_DATA, false,
                        update -> Objects.nonNull(update.getMessage().getWebAppData()), Collections.emptyList()),
                // invoice
                new HandleTypeFind(HandleType.INVOICE, false,
                        update -> Objects.equals(update.getMessage().hasInvoice(), Boolean.TRUE), Collections.emptyList()),
                // dice
                new HandleTypeFind(HandleType.DICE, false,
                        update -> Objects.equals(update.getMessage().hasDice(), Boolean.TRUE), Collections.emptyList())
        );
    }
}
