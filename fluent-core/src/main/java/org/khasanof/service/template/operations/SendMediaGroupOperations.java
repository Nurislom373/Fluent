package org.khasanof.service.template.operations;

import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.service.template.operations
 * @since 2/17/2024 5:15 PM
 */
public interface SendMediaGroupOperations {

    List<Message> sendMediaGroup(List<InputMedia> medias);

    List<Message> sendMediaGroup(List<InputMedia> medias, Long chatId);

    List<Message> sendMediaGroup(List<InputMedia> medias, Integer replyMessageId);

    List<Message> sendMediaGroup(List<InputMedia> medias, Boolean disableNotification);

    List<Message> sendMediaGroup(List<InputMedia> medias, Long chatId, Integer replyMessageId);

    List<Message> sendMediaGroup(List<InputMedia> medias, Long chatId, Boolean disableNotification);

    List<Message> sendMediaGroup(List<InputMedia> medias, Integer replyMessageId, Boolean disableNotification);

    List<Message> sendMediaGroup(List<InputMedia> medias, Long chatId, Integer replyMessageId, Boolean disableNotification);

    List<Message> sendMediaGroup(SendMediaGroup mediaGroup);
}
