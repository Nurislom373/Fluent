package org.khasanof.service.template;

import org.jetbrains.annotations.NotNull;
import org.khasanof.FluentBot;
import org.khasanof.context.FluentContextHolder;
import org.khasanof.context.singleton.GenericSingleton;
import org.khasanof.models.Round;
import org.khasanof.utils.UpdateUtils;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodBoolean;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.pinnedmessages.PinChatMessage;
import org.telegram.telegrambots.meta.api.methods.pinnedmessages.UnpinAllChatMessages;
import org.telegram.telegrambots.meta.api.methods.pinnedmessages.UnpinChatMessage;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import static org.khasanof.context.FluentContextHolder.getCurrentUpdate;
import static org.khasanof.utils.BaseUtils.notNull;

/**
 * A "template" that plays a central role in Fluent Framework is introduced. It is named FluentTemplate and serves as an
 * interface defining basic operations for working with a Telegram Bot. These operations encapsulate the essential actions for sending messages.
 * <br/>
 * <br/>
 *
 * @author Nurislom
 * @see org.khasanof.service.template
 * @since 1/23/2024 9:28 PM
 */
public class DefaultFluentTemplate implements FluentTemplate {

    private FluentBot instance;
    private final GenericSingleton<FluentBot> singletonBot;

    public DefaultFluentTemplate(GenericSingleton<FluentBot> singletonBot) {
        this.singletonBot = singletonBot;
    }

    @Override
    public Message sendText(String text) {
        return tryExecuteBotMethod(sendMessageBuilder(text, null, null, null, null, null));
    }

    @Override
    public Message sendText(String text, Long chatId) {
        return tryExecuteBotMethod(sendMessageBuilder(text, chatId, null, null, null, null));
    }

    @Override
    public Message sendText(String text, Integer replyMessageId) {
        return tryExecuteBotMethod(sendMessageBuilder(text, null, replyMessageId, null, null, null));
    }

    @Override
    public Message sendText(String text, ReplyKeyboard replyKeyboard) {
        return tryExecuteBotMethod(sendMessageBuilder(text, null, null, replyKeyboard, null, null));
    }

    @Override
    public Message sendText(String text, Boolean disableNotification) {
        return tryExecuteBotMethod(sendMessageBuilder(text, null, null, null, null, disableNotification));
    }

    @Override
    public Message sendText(String text, String parseMode) {
        return tryExecuteBotMethod(sendMessageBuilder(text, null, null, null, parseMode, null));
    }

    @Override
    public Message sendText(String text, Long chatId, Integer replyMessageId) {
        return tryExecuteBotMethod(sendMessageBuilder(text, chatId, replyMessageId, null, null, null));
    }

    @Override
    public Message sendText(String text, Long chatId, ReplyKeyboard replyKeyboard) {
        return tryExecuteBotMethod(sendMessageBuilder(text, chatId, null, replyKeyboard, null, null));
    }

    @Override
    public Message sendText(String text, Long chatId, Boolean disableNotification) {
        return tryExecuteBotMethod(sendMessageBuilder(text, chatId, null, null, null, disableNotification));
    }

    @Override
    public Message sendText(String text, Long chatId, String parseMode) {
        return tryExecuteBotMethod(sendMessageBuilder(text, chatId, null, null, parseMode, null));
    }

    @Override
    public Message sendText(String text, Integer replyMessageId, ReplyKeyboard replyKeyboard) {
        return tryExecuteBotMethod(sendMessageBuilder(text, null, replyMessageId, replyKeyboard, null, null));
    }

    @Override
    public Message sendText(String text, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteBotMethod(sendMessageBuilder(text, null, replyMessageId, null, null, disableNotification));
    }

    @Override
    public Message sendText(String text, Integer replyMessageId, String parseMode) {
        return tryExecuteBotMethod(sendMessageBuilder(text, null, replyMessageId, null, parseMode, null));
    }

    @Override
    public Message sendText(String text, ReplyKeyboard replyKeyboard, Boolean disableNotification) {
        return tryExecuteBotMethod(sendMessageBuilder(text, null, null, replyKeyboard, null, disableNotification));
    }

    @Override
    public Message sendText(String text, ReplyKeyboard replyKeyboard, String parseMode) {
        return tryExecuteBotMethod(sendMessageBuilder(text, null, null, replyKeyboard, parseMode, null));
    }

    @Override
    public Message sendText(String text, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard) {
        return tryExecuteBotMethod(sendMessageBuilder(text, chatId, replyMessageId, replyKeyboard, null, null));
    }

    @Override
    public Message sendText(String text, Long chatId, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteBotMethod(sendMessageBuilder(text, chatId, replyMessageId, null, null, disableNotification));
    }

    @Override
    public Message sendText(String text, Long chatId, Integer replyMessageId, String parseMode) {
        return tryExecuteBotMethod(sendMessageBuilder(text, chatId, replyMessageId, null, parseMode, null));
    }

    @Override
    public Message sendText(String text, Long chatId, ReplyKeyboard replyKeyboard, Boolean disableNotification) {
        return tryExecuteBotMethod(sendMessageBuilder(text, chatId, null, replyKeyboard, null, disableNotification));
    }

    @Override
    public Message sendText(String text, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard, String parseMode) {
        return tryExecuteBotMethod(sendMessageBuilder(text, chatId, replyMessageId, replyKeyboard, parseMode, null));
    }

    @Override
    public Message sendText(String text, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard, String parseMode, Boolean disableNotification) {
        return tryExecuteBotMethod(sendMessageBuilder(text, chatId, replyMessageId, replyKeyboard, parseMode, disableNotification));
    }

    @Override
    public Message sendMessage(SendMessage message) {
        notNull(message, "message param must not be null!");
        message.setChatId(getChatId(Long.valueOf(message.getChatId())));
        return tryExecuteBotMethod(message);
    }

    @Override
    public Message sendDocument(File file) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), null, null, null, null, null));
    }

    @Override
    public Message sendDocument(File file, Long chatId) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), chatId, null, null, null, null));
    }

    @Override
    public Message sendDocument(File file, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), null, null, replyKeyboard, null, null));
    }

    @Override
    public Message sendDocument(File file, Boolean disableNotification) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), null, null, null, null, disableNotification));
    }

    @Override
    public Message sendDocument(File file, String caption) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), null, null, null, caption, null));
    }

    @Override
    public Message sendDocument(File file, Integer replyMessageId) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), null, replyMessageId, null, null, null));
    }

    @Override
    public Message sendDocument(File file, Long chatId, String caption) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), chatId, null, null, caption, null));
    }

    @Override
    public Message sendDocument(File file, Long chatId, Integer replyMessageId) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), chatId, replyMessageId, null, null, null));
    }

    @Override
    public Message sendDocument(File file, Long chatId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), chatId, null, replyKeyboard, null, null));
    }

    @Override
    public Message sendDocument(File file, Long chatId, Boolean disableNotification) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), chatId, null, null, null, disableNotification));
    }

    @Override
    public Message sendDocument(File file, String caption, Integer replyMessageId) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), null, replyMessageId, null, caption, null));
    }

    @Override
    public Message sendDocument(File file, String caption, ReplyKeyboard replyMarkup) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), null, null, replyMarkup, caption, null));
    }

    @Override
    public Message sendDocument(File file, String caption, Boolean disableNotification) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), null, null, null, caption, disableNotification));
    }

    @Override
    public Message sendDocument(File file, Integer replyMessageId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), null, replyMessageId, replyKeyboard, null, null));
    }

    @Override
    public Message sendDocument(File file, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), null, replyMessageId, null, null, disableNotification));
    }

    @Override
    public Message sendDocument(File file, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), null, null, replyMarkup, null, disableNotification));
    }

    @Override
    public Message sendDocument(File file, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), null, replyMessageId, replyMarkup, caption, null));
    }

    @Override
    public Message sendDocument(File file, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), null, null, replyMarkup, caption, disableNotification));
    }

    @Override
    public Message sendDocument(File file, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), null, replyMessageId, replyMarkup, caption, disableNotification));
    }

    @Override
    public Message sendDocument(File file, Long chatId, String caption, ReplyKeyboard replyMarkup) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), chatId, null, replyMarkup, caption, null));
    }

    @Override
    public Message sendDocument(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), chatId, replyMessageId, replyMarkup, caption, null));
    }

    @Override
    public Message sendDocument(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), chatId, null, replyMarkup, caption, disableNotification));
    }

    @Override
    public Message sendDocument(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), chatId, replyMessageId, replyMarkup, caption, disableNotification));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(inputStream, filename), null, null, null, null, null));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename, Long chatId) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(inputStream, filename), chatId, null, null, null, null));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename, String caption) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(inputStream, filename), null, null, null, caption, null));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename, Integer replyMessageId) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(inputStream, filename), null, replyMessageId, null, null, null));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(inputStream, filename), null, null, replyKeyboard, null, null));
    }

    @Override
    public Message sendDocument(InputStream stream, String filename, Boolean disableNotification) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(stream, filename), null, null, null, null, disableNotification));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename, Long chatId, String caption) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(inputStream, filename), chatId, null, null, caption, null));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename, Long chatId, Integer replyMessageId) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(inputStream, filename), chatId, replyMessageId, null, null, null));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename, Long chatId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(inputStream, filename), chatId, null, replyKeyboard, null, null));
    }

    @Override
    public Message sendDocument(InputStream stream, String filename, Long chatId, Boolean disableNotification) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(stream, filename), chatId, null, null, null, disableNotification));
    }

    @Override
    public Message sendDocument(InputStream stream, String filename, String caption, Integer replyMessageId) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(stream, filename), null, replyMessageId, null, caption, null));
    }

    @Override
    public Message sendDocument(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(stream, filename), null, null, replyMarkup, caption, null));
    }

    @Override
    public Message sendDocument(InputStream stream, String filename, String caption, Boolean disableNotification) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(stream, filename), null, null, null, caption, disableNotification));
    }

    @Override
    public Message sendDocument(InputStream stream, String filename, Integer replyMessageId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(stream, filename), null, replyMessageId, replyKeyboard, null, null));
    }

    @Override
    public Message sendDocument(InputStream stream, String filename, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(stream, filename), null, replyMessageId, null, null, disableNotification));
    }

    @Override
    public Message sendDocument(InputStream stream, String filename, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(stream, filename), null, null, replyMarkup, null, disableNotification));
    }

    @Override
    public Message sendDocument(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(stream, filename), null, replyMessageId, replyMarkup, caption, null));
    }

    @Override
    public Message sendDocument(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(stream, filename), null, null, replyMarkup, caption, disableNotification));
    }

    @Override
    public Message sendDocument(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(stream, filename), null, replyMessageId, replyMarkup, caption, disableNotification));
    }

    @Override
    public Message sendDocument(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(stream, filename), chatId, null, replyMarkup, caption, null));
    }

    @Override
    public Message sendDocument(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(stream, filename), chatId, replyMessageId, replyMarkup, caption, null));
    }

    @Override
    public Message sendDocument(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(stream, filename), chatId, null, replyMarkup, caption, disableNotification));
    }

    @Override
    public Message sendDocument(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(stream, filename), chatId, replyMessageId, replyMarkup, caption, disableNotification));
    }

    @Override
    public Message sendDocument(SendDocument document) {
        notNull(document, "document param must not be null!");
        document.setChatId(getChatId(Long.valueOf(document.getChatId())));
        return tryExecuteSendDocument(document);
    }

    @Override
    public Message sendAudio(File file) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), null, null, null, null, null, null));
    }

    @Override
    public Message sendAudio(File file, Long chatId) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), chatId, null, null, null, null, null));
    }

    @Override
    public Message sendAudio(File file, String caption) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), null, null, null, caption, null, null));
    }

    @Override
    public Message sendAudio(File file, Integer replyMessageId) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), null, replyMessageId, null, null, null, null));
    }

    @Override
    public Message sendAudio(File file, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), null, null, replyKeyboard, null, null, null));
    }

    @Override
    public Message sendAudio(File file, Boolean disableNotification) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), null, null, null, null, null, null));
    }

    @Override
    public Message sendAudio(File file, Long chatId, String caption) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), chatId, null, null, caption, null, null));
    }

    @Override
    public Message sendAudio(File file, Long chatId, Integer replyMessageId) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), null, replyMessageId, null, null, null, null));
    }

    @Override
    public Message sendAudio(File file, Long chatId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), chatId, null, replyKeyboard, null, null, null));
    }

    @Override
    public Message sendAudio(File file, Long chatId, Boolean disableNotification) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), chatId, null, null, null, null, disableNotification));
    }

    @Override
    public Message sendAudio(File file, String caption, Integer replyMessageId) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), null, replyMessageId, null, caption, null, null));
    }

    @Override
    public Message sendAudio(File file, String caption, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), null, null, replyKeyboard, caption, null, null));
    }

    @Override
    public Message sendAudio(File file, String caption, Boolean disableNotification) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), null, null, null, caption, null, disableNotification));
    }

    @Override
    public Message sendAudio(File file, String caption, String title) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), null, null, null, caption, title, null));
    }

    @Override
    public Message sendAudio(File file, Integer replyMessageId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), null, replyMessageId, replyKeyboard, null, null, null));
    }

    @Override
    public Message sendAudio(File file, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), null, replyMessageId, null, null, null, disableNotification));
    }

    @Override
    public Message sendAudio(File file, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), null, null, replyMarkup, null, null, disableNotification));
    }

    @Override
    public Message sendAudio(File file, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), null, replyMessageId, replyMarkup, caption, null, null));
    }

    @Override
    public Message sendAudio(File file, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), null, null, replyMarkup, caption, null, disableNotification));
    }

    @Override
    public Message sendAudio(File file, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), null, replyMessageId, replyMarkup, caption, null, disableNotification));
    }

    @Override
    public Message sendAudio(File file, Long chatId, String caption, ReplyKeyboard replyMarkup) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), chatId, null, replyMarkup, caption, null, null));
    }

    @Override
    public Message sendAudio(File file, Long chatId, String caption, String title, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), chatId, null, replyKeyboard, caption, title, null));
    }

    @Override
    public Message sendAudio(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), chatId, replyMessageId, replyMarkup, caption, null, null));
    }

    @Override
    public Message sendAudio(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), chatId, null, replyMarkup, caption, null, disableNotification));
    }

    @Override
    public Message sendAudio(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), chatId, replyMessageId, replyMarkup, caption, null, disableNotification));
    }

    @Override
    public Message sendAudio(File file, Long chatId, String caption, String title, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), chatId, replyMessageId, replyMarkup, caption, title, disableNotification));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), null, null, null, null, null, null));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, Long chatId) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), chatId, null, null, null, null, null));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, String caption) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), null, null, null, caption, null, null));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, Integer replyMessageId) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), null, replyMessageId, null, null, null, null));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), null, null, replyKeyboard, null, null, null));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, Long chatId, String caption) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), chatId, null, null, caption, null, null));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, Long chatId, Integer replyMessageId) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), chatId, replyMessageId, null, null, null, null));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, Long chatId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), chatId, null, replyKeyboard, null, null, null));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, String caption, Integer replyMessageId) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), null, replyMessageId, null, caption, null, null));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, String caption, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), null, null, replyKeyboard, caption, null, null));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, String caption, String title) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), null, null, null, caption, title, null));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, String caption, String title, Integer replyMessageId) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), null, replyMessageId, null, caption, title, null));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, String caption, String title, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), null, null, replyKeyboard, caption, title, null));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, Long chatId, String caption, String title) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), chatId, null, null, caption, title, null));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, Long chatId, String caption, String title, Integer replyMessageId) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), chatId, replyMessageId, null, caption, title, null));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, Long chatId, String caption, String title, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), chatId, null, replyKeyboard, caption, title, null));
    }

    @Override
    public Message sendAudio(SendAudio sendAudio) {
        notNull(sendAudio, "sendAudio param must not be null!");
        sendAudio.setChatId(getChatId(Long.valueOf(sendAudio.getChatId())));
        return tryExecuteSendAudio(sendAudio);
    }

    @Override
    public Message sendPhoto(File file) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(file), null, null, null, null, null));
    }

    @Override
    public Message sendPhoto(File file, Long chatId) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(file), chatId, null, null, null, null));
    }

    @Override
    public Message sendPhoto(File file, String caption) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(file), null, caption, null, null, null));
    }

    @Override
    public Message sendPhoto(File file, Integer replyMessageId) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(file), null, null, null, replyMessageId, null));
    }

    @Override
    public Message sendPhoto(File file, ReplyKeyboard replyMarkup) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(file), null, null, replyMarkup, null, null));
    }

    @Override
    public Message sendPhoto(File file, Boolean disableNotification) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(file), null, null, null, null, disableNotification));
    }

    @Override
    public Message sendPhoto(File file, Long chatId, String caption) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(file), chatId, caption, null, null, null));
    }

    @Override
    public Message sendPhoto(File file, Long chatId, Integer replyMessageId) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(file), chatId, null, null, replyMessageId, null));
    }

    @Override
    public Message sendPhoto(File file, Long chatId, ReplyKeyboard replyMarkup) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(file), chatId, null, replyMarkup, null, null));
    }

    @Override
    public Message sendPhoto(File file, Long chatId, Boolean disableNotification) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(file), chatId, null, null, null, disableNotification));
    }

    @Override
    public Message sendPhoto(File file, String caption, Integer replyMessageId) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(file), null, caption, null, replyMessageId, null));
    }

    @Override
    public Message sendPhoto(File file, String caption, ReplyKeyboard replyMarkup) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(file), null, caption, replyMarkup, null, null));
    }

    @Override
    public Message sendPhoto(File file, String caption, Boolean disableNotification) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(file), null, caption, null, null, disableNotification));
    }

    @Override
    public Message sendPhoto(File file, Integer replyMessageId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(file), null, null, replyKeyboard, replyMessageId, null));
    }

    @Override
    public Message sendPhoto(File file, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(file), null, null, null, replyMessageId, disableNotification));
    }

    @Override
    public Message sendPhoto(File file, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(file), null, null, replyMarkup, null, disableNotification));
    }

    @Override
    public Message sendPhoto(File file, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(file), null, caption, replyMarkup, replyMessageId, null));
    }

    @Override
    public Message sendPhoto(File file, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(file), null, caption, replyMarkup, null, disableNotification));
    }

    @Override
    public Message sendPhoto(File file, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(file), null, caption, replyMarkup, replyMessageId, disableNotification));
    }

    @Override
    public Message sendPhoto(File file, Long chatId, String caption, ReplyKeyboard replyMarkup) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(file), chatId, caption, replyMarkup, null, null));
    }

    @Override
    public Message sendPhoto(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(file), chatId, caption, replyMarkup, replyMessageId, null));
    }

    @Override
    public Message sendPhoto(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(file), chatId, caption, replyMarkup, null, disableNotification));
    }

    @Override
    public Message sendPhoto(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(file), chatId, caption, replyMarkup, replyMessageId, disableNotification));
    }

    @Override
    public Message sendPhoto(InputStream stream, String filename) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(stream, filename), null, null, null, null, null));
    }

    @Override
    public Message sendPhoto(InputStream stream, String filename, Long chatId) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(stream, filename), chatId, null, null, null, null));
    }

    @Override
    public Message sendPhoto(InputStream stream, String filename, String caption) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(stream, filename), null, caption, null, null, null));
    }

    @Override
    public Message sendPhoto(InputStream stream, String filename, Integer replyMessageId) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(stream, filename), null, null, null, replyMessageId, null));
    }

    @Override
    public Message sendPhoto(InputStream stream, String filename, ReplyKeyboard replyMarkup) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(stream, filename), null, null, replyMarkup, null, null));
    }

    @Override
    public Message sendPhoto(InputStream stream, String filename, Boolean disableNotification) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(stream, filename), null, null, null, null, disableNotification));
    }

    @Override
    public Message sendPhoto(InputStream stream, String filename, Long chatId, String caption) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(stream, filename), chatId, caption, null, null, null));
    }

    @Override
    public Message sendPhoto(InputStream stream, String filename, Long chatId, Integer replyMessageId) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(stream, filename), chatId, null, null, replyMessageId, null));
    }

    @Override
    public Message sendPhoto(InputStream stream, String filename, Long chatId, ReplyKeyboard replyMarkup) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(stream, filename), chatId, null, replyMarkup, null, null));
    }

    @Override
    public Message sendPhoto(InputStream stream, String filename, Long chatId, Boolean disableNotification) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(stream, filename), chatId, null, null, null, disableNotification));
    }

    @Override
    public Message sendPhoto(InputStream stream, String filename, String caption, Integer replyMessageId) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(stream, filename), null, caption, null, replyMessageId, null));
    }

    @Override
    public Message sendPhoto(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(stream, filename), null, caption, replyMarkup, null, null));
    }

    @Override
    public Message sendPhoto(InputStream stream, String filename, String caption, Boolean disableNotification) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(stream, filename), null, caption, null, null, disableNotification));
    }

    @Override
    public Message sendPhoto(InputStream stream, String filename, Integer replyMessageId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(stream, filename), null, null, replyKeyboard, replyMessageId, null));
    }

    @Override
    public Message sendPhoto(InputStream stream, String filename, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(stream, filename), null, null, null, replyMessageId, disableNotification));
    }

    @Override
    public Message sendPhoto(InputStream stream, String filename, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(stream, filename), null, null, replyMarkup, null, disableNotification));
    }

    @Override
    public Message sendPhoto(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(stream, filename), chatId, caption, replyMarkup, null, null));
    }

    @Override
    public Message sendPhoto(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(stream, filename), null, caption, replyMarkup, replyMessageId, null));
    }

    @Override
    public Message sendPhoto(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(stream, filename), null, caption, replyMarkup, null, disableNotification));
    }

    @Override
    public Message sendPhoto(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(stream, filename), null, caption, replyMarkup, replyMessageId, disableNotification));
    }

    @Override
    public Message sendPhoto(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(stream, filename), chatId, caption, replyMarkup, replyMessageId, null));
    }

    @Override
    public Message sendPhoto(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(stream, filename), chatId, caption, replyMarkup, null, disableNotification));
    }

    @Override
    public Message sendPhoto(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendPhoto(sendPhotoBuilder(new InputFile(stream, filename), chatId, caption, replyMarkup, replyMessageId, disableNotification));
    }

    @Override
    public Message sendPhoto(SendPhoto photo) {
        notNull(photo, "photo param must not be null!");
        photo.setChatId(getChatId(Long.valueOf(photo.getChatId())));
        return tryExecuteSendPhoto(photo);
    }

    @Override
    public Message sendDice(String emoji) {
        return tryExecuteBotMethod(sendDiceBuilder(emoji, null, null, null));
    }

    @Override
    public Message sendDice(String emoji, Long chatId) {
        return tryExecuteBotMethod(sendDiceBuilder(emoji, chatId, null, null));
    }

    @Override
    public Message sendDice(String emoji, Integer replyToMessageId) {
        return tryExecuteBotMethod(sendDiceBuilder(emoji, null, replyToMessageId, null));
    }

    @Override
    public Message sendDice(String emoji, ReplyKeyboard replyMarkup) {
        return tryExecuteBotMethod(sendDiceBuilder(emoji, null, null, replyMarkup));
    }

    @Override
    public Message sendDice(String emoji, Long chatId, Integer replyMessageId) {
        return tryExecuteBotMethod(sendDiceBuilder(emoji, chatId, replyMessageId, null));
    }

    @Override
    public Message sendDice(String emoji, Long chatId, ReplyKeyboard replyKeyboard) {
        return tryExecuteBotMethod(sendDiceBuilder(emoji, chatId, null, replyKeyboard));
    }

    @Override
    public Message sendDice(String emoji, ReplyKeyboard replyKeyboard, Integer replyMessageId) {
        return tryExecuteBotMethod(sendDiceBuilder(emoji, null, replyMessageId, replyKeyboard));
    }

    @Override
    public Message sendDice(String emoji, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard) {
        return tryExecuteBotMethod(sendDiceBuilder(emoji, chatId, replyMessageId, replyKeyboard));
    }

    @Override
    public Message sendDice(SendDice sendDice) {
        notNull(sendDice, "sendDice param must not be null!");
        sendDice.setChatId(getChatId(Long.valueOf(sendDice.getChatId())));
        return tryExecuteBotMethod(sendDice);
    }

    @Override
    public Message sendVideoNote(File file) {
        return tryExecuteSendVideoNote(sendVideoNoteBuilder(new InputFile(file), null, null, null));
    }

    @Override
    public Message sendVideoNote(File file, Long chatId) {
        return tryExecuteSendVideoNote(sendVideoNoteBuilder(new InputFile(file), chatId, null, null));

    }

    @Override
    public Message sendVideoNote(File file, Integer replyToMessageId) {
        return tryExecuteSendVideoNote(sendVideoNoteBuilder(new InputFile(file), null, replyToMessageId, null));

    }

    @Override
    public Message sendVideoNote(File file, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVideoNote(sendVideoNoteBuilder(new InputFile(file), null, null, replyMarkup));

    }

    @Override
    public Message sendVideoNote(File file, Long chatId, Integer replyMessageId) {
        return tryExecuteSendVideoNote(sendVideoNoteBuilder(new InputFile(file), chatId, replyMessageId, null));

    }

    @Override
    public Message sendVideoNote(File file, Long chatId, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVideoNote(sendVideoNoteBuilder(new InputFile(file), chatId, null, replyMarkup));

    }


    @Override
    public Message sendVideoNote(File file, Integer replyToMessageId, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVideoNote(sendVideoNoteBuilder(new InputFile(file), null, replyToMessageId, replyMarkup));

    }


    @Override
    public Message sendVideoNote(File file, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendVideoNote(sendVideoNoteBuilder(new InputFile(file), chatId, replyMessageId, replyKeyboard));

    }

    @Override
    public Message sendVideoNote(InputStream stream, String filename) {
        return tryExecuteSendVideoNote(sendVideoNoteBuilder(new InputFile(stream, filename), null, null, null));
    }

    @Override
    public Message sendVideoNote(InputStream stream, String filename, Long chatId) {
        return tryExecuteSendVideoNote(sendVideoNoteBuilder(new InputFile(stream, filename), chatId, null, null));
    }

    @Override
    public Message sendVideoNote(InputStream stream, String filename, Integer replyToMessageId) {
        return tryExecuteSendVideoNote(sendVideoNoteBuilder(new InputFile(stream, filename), null, replyToMessageId, null));

    }

    @Override
    public Message sendVideoNote(InputStream stream, String filename, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVideoNote(sendVideoNoteBuilder(new InputFile(stream, filename), null, null, replyMarkup));

    }

    @Override
    public Message sendVideoNote(InputStream stream, String filename, Long chatId, Integer replyMessageId) {
        return tryExecuteSendVideoNote(sendVideoNoteBuilder(new InputFile(stream, filename), chatId, replyMessageId, null));

    }

    @Override
    public Message sendVideoNote(InputStream stream, String filename, Long chatId, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVideoNote(sendVideoNoteBuilder(new InputFile(stream, filename), chatId, null, replyMarkup));

    }

    @Override
    public Message sendVideoNote(InputStream stream, String filename, Integer replyToMessageId, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVideoNote(sendVideoNoteBuilder(new InputFile(stream, filename), null, replyToMessageId, replyMarkup));

    }

    @Override
    public Message sendVideoNote(InputStream stream, String filename, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendVideoNote(sendVideoNoteBuilder(new InputFile(stream, filename), chatId, replyMessageId, replyKeyboard));
    }

    @Override
    public Message sendVideoNote(SendVideoNote sendVideoNote) {
        notNull(sendVideoNote, "sendVideoNote param must not be null!");
        sendVideoNote.setChatId(getChatId(Long.valueOf(sendVideoNote.getChatId())));
        return tryExecuteSendVideoNote(sendVideoNote);
    }

    @Override
    public Message sendVideo(File file) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(file), null, null, null, null));

    }

    @Override
    public Message sendVideo(File file, Long chatId) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(file), chatId, null, null, null));
    }

    @Override
    public Message sendVideo(File file, String caption) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(file), null, caption, null, null));
    }

    @Override
    public Message sendVideo(File file, Integer replyToMessageId) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(file), null, null, null, replyToMessageId));
    }

    @Override
    public Message sendVideo(File file, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(file), null, null, replyMarkup, null));
    }

    @Override
    public Message sendVideo(File file, Long chatId, String caption) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(file), chatId, caption, null, null));
    }

    @Override
    public Message sendVideo(File file, Long chatId, Integer replyMessageId) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(file), chatId, null, null, replyMessageId));
    }

    @Override
    public Message sendVideo(File file, Long chatId, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(file), chatId, null, replyMarkup, null));
    }

    @Override
    public Message sendVideo(File file, String caption, Integer replyMessageId) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(file), null, caption, null, replyMessageId));
    }

    @Override
    public Message sendVideo(File file, String caption, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(file), null, caption, replyMarkup, null));
    }

    @Override
    public Message sendVideo(File file, Integer replyToMessageId, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(file), null, null, replyMarkup, replyToMessageId));
    }

    @Override
    public Message sendVideo(File file, Long chatId, String caption, Integer replyMessageId) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(file), chatId, caption, null, replyMessageId));
    }

    @Override
    public Message sendVideo(File file, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(file), chatId, null, replyKeyboard, replyMessageId));
    }

    @Override
    public Message sendVideo(File file, Long chatId, String caption, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(file), chatId, caption, replyMarkup, null));
    }

    @Override
    public Message sendVideo(File file, String caption, Integer replyMessageId, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(file), null, caption, replyMarkup, replyMessageId));
    }

    @Override
    public Message sendVideo(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(file), chatId, caption, replyMarkup, replyMessageId));
    }

    @Override
    public Message sendVideo(InputStream stream, String filename) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(stream, filename), null, null, null, null));
    }

    @Override
    public Message sendVideo(InputStream stream, String filename, Long chatId) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(stream, filename), chatId, null, null, null));
    }

    @Override
    public Message sendVideo(InputStream stream, String filename, String caption) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(stream, filename), null, caption, null, null));
    }

    @Override
    public Message sendVideo(InputStream stream, String filename, Integer replyToMessageId) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(stream, filename), null, null, null, replyToMessageId));
    }

    @Override
    public Message sendVideo(InputStream stream, String filename, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(stream, filename), null, null, replyMarkup, null));
    }

    @Override
    public Message sendVideo(InputStream stream, String filename, Long chatId, String caption) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(stream, filename), chatId, caption, null, null));
    }

    @Override
    public Message sendVideo(InputStream stream, String filename, Long chatId, Integer replyMessageId) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(stream, filename), chatId, null, null, replyMessageId));
    }

    @Override
    public Message sendVideo(InputStream stream, String filename, Long chatId, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(stream, filename), chatId, null, replyMarkup, null));
    }

    @Override
    public Message sendVideo(InputStream stream, String filename, String caption, Integer replyMessageId) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(stream, filename), null, caption, null, replyMessageId));
    }

    @Override
    public Message sendVideo(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(stream, filename), null, caption, replyMarkup, null));
    }

    @Override
    public Message sendVideo(InputStream stream, String filename, Integer replyToMessageId, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(stream, filename), null, null, replyMarkup, replyToMessageId));
    }

    @Override
    public Message sendVideo(InputStream stream, String filename, Long chatId, String caption, Integer replyMessageId) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(stream, filename), chatId, caption, null, replyMessageId));
    }

    @Override
    public Message sendVideo(InputStream stream, String filename, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(stream, filename), chatId, null, replyKeyboard, replyMessageId));
    }

    @Override
    public Message sendVideo(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(stream, filename), chatId, caption, replyMarkup, null));
    }

    @Override
    public Message sendVideo(InputStream stream, String filename, String caption, Integer replyMessageId, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(stream, filename), null, caption, replyMarkup, replyMessageId));
    }

    @Override
    public Message sendVideo(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId) {
        return tryExecuteSendVideo(sendVideoBuilder(new InputFile(stream, filename), chatId, caption, replyMarkup, replyMessageId));
    }

    @Override
    public Message sendVideo(SendVideo sendVideo) {
        notNull(sendVideo, "sendVideo param must not be null!");
        sendVideo.setChatId(getChatId(Long.valueOf(sendVideo.getChatId())));
        return tryExecuteSendVideo(sendVideo);
    }

    @Override
    public Message sendVoice(File file) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(file), null, null, null, null));
    }

    @Override
    public Message sendVoice(File file, Long chatId) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(file), chatId, null, null, null));
    }

    @Override
    public Message sendVoice(File file, String caption) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(file), null, caption, null, null));
    }

    @Override
    public Message sendVoice(File file, Integer replyToMessageId) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(file), null, null, null, replyToMessageId));
    }

    @Override
    public Message sendVoice(File file, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(file), null, null, replyMarkup, null));
    }

    @Override
    public Message sendVoice(File file, Long chatId, String caption) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(file), chatId, caption, null, null));
    }

    @Override
    public Message sendVoice(File file, Long chatId, Integer replyMessageId) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(file), chatId, null, null, replyMessageId));
    }

    @Override
    public Message sendVoice(File file, Long chatId, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(file), chatId, null, replyMarkup, null));
    }

    @Override
    public Message sendVoice(File file, String caption, Integer replyMessageId) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(file), null, caption, null, replyMessageId));
    }

    @Override
    public Message sendVoice(File file, String caption, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(file), null, caption, replyMarkup, null));
    }

    @Override
    public Message sendVoice(File file, Integer replyToMessageId, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(file), null, null, replyMarkup, replyToMessageId));
    }

    @Override
    public Message sendVoice(File file, Long chatId, String caption, Integer replyMessageId) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(file), chatId, caption, null, replyMessageId));
    }

    @Override
    public Message sendVoice(File file, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(file), chatId, null, replyKeyboard, replyMessageId));
    }

    @Override
    public Message sendVoice(File file, Long chatId, String caption, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(file), chatId, caption, replyMarkup, null));
    }

    @Override
    public Message sendVoice(File file, String caption, Integer replyMessageId, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(file), null, caption, replyMarkup, replyMessageId));
    }

    @Override
    public Message sendVoice(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(file), chatId, caption, replyMarkup, replyMessageId));
    }

    @Override
    public Message sendVoice(InputStream stream, String filename) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(stream, filename), null, null, null, null));
    }

    @Override
    public Message sendVoice(InputStream stream, String filename, Long chatId) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(stream, filename), chatId, null, null, null));
    }

    @Override
    public Message sendVoice(InputStream stream, String filename, String caption) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(stream, filename), null, caption, null, null));
    }

    @Override
    public Message sendVoice(InputStream stream, String filename, Integer replyToMessageId) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(stream, filename), null, null, null, replyToMessageId));
    }

    @Override
    public Message sendVoice(InputStream stream, String filename, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(stream, filename), null, null, replyMarkup, null));
    }

    @Override
    public Message sendVoice(InputStream stream, String filename, Long chatId, String caption) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(stream, filename), chatId, caption, null, null));
    }

    @Override
    public Message sendVoice(InputStream stream, String filename, Long chatId, Integer replyMessageId) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(stream, filename), chatId, null, null, replyMessageId));
    }

    @Override
    public Message sendVoice(InputStream stream, String filename, Long chatId, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(stream, filename), chatId, null, replyMarkup, null));
    }

    @Override
    public Message sendVoice(InputStream stream, String filename, String caption, Integer replyMessageId) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(stream, filename), null, caption, null, replyMessageId));
    }

    @Override
    public Message sendVoice(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(stream, filename), null, caption, replyMarkup, null));
    }

    @Override
    public Message sendVoice(InputStream stream, String filename, Integer replyToMessageId, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(stream, filename), null, null, replyMarkup, replyToMessageId));
    }

    @Override
    public Message sendVoice(InputStream stream, String filename, Long chatId, String caption, Integer replyMessageId) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(stream, filename), chatId, caption, null, replyMessageId));
    }

    @Override
    public Message sendVoice(InputStream stream, String filename, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(stream, filename), chatId, null, replyKeyboard, replyMessageId));
    }

    @Override
    public Message sendVoice(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(stream, filename), chatId, caption, replyMarkup, null));
    }

    @Override
    public Message sendVoice(InputStream stream, String filename, String caption, Integer replyMessageId, ReplyKeyboard replyMarkup) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(stream, filename), null, caption, replyMarkup, replyMessageId));
    }

    @Override
    public Message sendVoice(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId) {
        return tryExecuteSendVoice(sendVoiceBuilder(new InputFile(stream, filename), chatId, caption, replyMarkup, replyMessageId));
    }

    @Override
    public Message sendVoice(SendVoice sendVoice) {
        notNull(sendVoice, "sendVoice param must not be null!");
        sendVoice.setChatId(getChatId(Long.valueOf(sendVoice.getChatId())));
        return tryExecuteSendVoice(sendVoice);
    }

    @Override
    public Message forwardMessage(String fromChatId, Integer messageId) {
        return tryExecuteBotMethod(forwardMessageBuilder(fromChatId, null, messageId));
    }

    @Override
    public Message forwardMessage(String fromChatId, Integer messageId, Long chatId) {
        return tryExecuteBotMethod(forwardMessageBuilder(fromChatId, chatId, messageId));
    }

    @Override
    public Message forwardMessage(ForwardMessage forwardMessage) {
        notNull(forwardMessage, "forwardMessage param must not be null!");
        forwardMessage.setChatId(getChatId(Long.valueOf(forwardMessage.getChatId())));
        return tryExecuteBotMethod(forwardMessage);
    }

    @Override
    public Boolean sendAnswerCallbackQuery(String text) {
        return tryExecuteAnswerQueryMethod(answerCallbackQueryBuilder(text, null, null, null));
    }

    @Override
    public Boolean sendAnswerCallbackQuery(String text, String callbackQueryId) {
        return tryExecuteAnswerQueryMethod(answerCallbackQueryBuilder(text, callbackQueryId, null, null));
    }

    @Override
    public Boolean sendAnswerCallbackQuery(String text, Boolean showAlert) {
        return tryExecuteAnswerQueryMethod(answerCallbackQueryBuilder(text, null, null, showAlert));
    }

    @Override
    public Boolean sendAnswerCallbackQuery(String text, String callbackQueryId, String url) {
        return tryExecuteAnswerQueryMethod(answerCallbackQueryBuilder(text, text, url, null));
    }

    @Override
    public Boolean sendAnswerCallbackQuery(String text, String callbackQueryId, Boolean showAlert) {
        return tryExecuteAnswerQueryMethod(answerCallbackQueryBuilder(text, callbackQueryId, null, showAlert));
    }

    @Override
    public Boolean sendAnswerCallbackQuery(String text, String callbackQueryId, String url, Boolean showAlert) {
        return tryExecuteAnswerQueryMethod(answerCallbackQueryBuilder(text, callbackQueryId, url, showAlert));
    }

    @Override
    public Boolean sendAnswerCallbackQuery(AnswerCallbackQuery answerCallbackQuery) {
        notNull(answerCallbackQuery, "answerCallbackQuery param must not be null!");
        answerCallbackQuery.setCallbackQueryId(getCallbackQueryId(answerCallbackQuery.getCallbackQueryId()));
        return tryExecuteAnswerQueryMethod(answerCallbackQuery);
    }

    @Override
    public Boolean sendAnswerInlineQuery(List<InlineQueryResult> results) {
        return tryExecuteAnswerQueryMethod(answerInlineQueryBuilder(results, null, null, null));
    }

    @Override
    public Boolean sendAnswerInlineQuery(List<InlineQueryResult> results, String inlineQueryId) {
        return tryExecuteAnswerQueryMethod(answerInlineQueryBuilder(results, inlineQueryId, null, null));
    }

    @Override
    public Boolean sendAnswerInlineQuery(List<InlineQueryResult> results, Integer cacheTime) {
        return tryExecuteAnswerQueryMethod(answerInlineQueryBuilder(results, null, cacheTime, null));
    }

    @Override
    public Boolean sendAnswerInlineQuery(List<InlineQueryResult> results, Boolean isPersonal) {
        return tryExecuteAnswerQueryMethod(answerInlineQueryBuilder(results, null, null, isPersonal));
    }

    @Override
    public Boolean sendAnswerInlineQuery(List<InlineQueryResult> results, String inlineQueryId, Integer cacheTime) {
        return tryExecuteAnswerQueryMethod(answerInlineQueryBuilder(results, inlineQueryId, cacheTime, null));
    }

    @Override
    public Boolean sendAnswerInlineQuery(List<InlineQueryResult> results, String inlineQueryId, Boolean isPersonal) {
        return tryExecuteAnswerQueryMethod(answerInlineQueryBuilder(results, inlineQueryId, null, isPersonal));
    }

    @Override
    public Boolean sendAnswerInlineQuery(List<InlineQueryResult> results, String inlineQueryId, Integer cacheTime, Boolean isPersonal) {
        return tryExecuteAnswerQueryMethod(answerInlineQueryBuilder(results, inlineQueryId, cacheTime, isPersonal));
    }

    @Override
    public Boolean sendAnswerInlineQuery(AnswerInlineQuery answerInlineQuery) {
        notNull(answerInlineQuery, "answerInlineQuery param must not be null!");
        answerInlineQuery.setInlineQueryId(getInlineQueryId(answerInlineQuery.getInlineQueryId()));
        return tryExecuteAnswerQueryMethod(answerInlineQuery);
    }

    @Override
    public Message sendAnimation(File file) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(file), null, null, null, null, null));
    }

    @Override
    public Message sendAnimation(File file, Long chatId) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(file), chatId, null, null, null, null));
    }

    @Override
    public Message sendAnimation(File file, String caption) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(file), null, caption, null, null, null));
    }

    @Override
    public Message sendAnimation(File file, Integer replyMessageId) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(file), null, null, null, replyMessageId, null));
    }

    @Override
    public Message sendAnimation(File file, ReplyKeyboard replyMarkup) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(file), null, null, replyMarkup, null, null));
    }

    @Override
    public Message sendAnimation(File file, Boolean disableNotification) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(file), null, null, null, null, disableNotification));
    }

    @Override
    public Message sendAnimation(File file, Long chatId, String caption) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(file), chatId, caption, null, null, null));
    }

    @Override
    public Message sendAnimation(File file, Long chatId, Integer replyMessageId) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(file), chatId, null, null, replyMessageId, null));
    }

    @Override
    public Message sendAnimation(File file, Long chatId, ReplyKeyboard replyMarkup) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(file), chatId, null, replyMarkup, null, null));
    }

    @Override
    public Message sendAnimation(File file, Long chatId, Boolean disableNotification) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(file), chatId, null, null, null, disableNotification));
    }

    @Override
    public Message sendAnimation(File file, String caption, Integer replyMessageId) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(file), null, caption, null, replyMessageId, null));
    }

    @Override
    public Message sendAnimation(File file, String caption, ReplyKeyboard replyMarkup) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(file), null, caption, replyMarkup, null, null));
    }

    @Override
    public Message sendAnimation(File file, String caption, Boolean disableNotification) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(file), null, caption, null, null, disableNotification));
    }

    @Override
    public Message sendAnimation(File file, Integer replyMessageId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(file), null, null, replyKeyboard, replyMessageId, null));
    }

    @Override
    public Message sendAnimation(File file, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(file), null, null, null, replyMessageId, disableNotification));
    }

    @Override
    public Message sendAnimation(File file, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(file), null, null, replyMarkup, null, disableNotification));
    }

    @Override
    public Message sendAnimation(File file, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(file), null, caption, replyMarkup, replyMessageId, null));
    }

    @Override
    public Message sendAnimation(File file, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(file), null, caption, replyMarkup, null, disableNotification));
    }

    @Override
    public Message sendAnimation(File file, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(file), null, caption, replyMarkup, replyMessageId, disableNotification));
    }

    @Override
    public Message sendAnimation(File file, Long chatId, String caption, ReplyKeyboard replyMarkup) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(file), chatId, caption, replyMarkup, null, null));
    }

    @Override
    public Message sendAnimation(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(file), chatId, caption, replyMarkup, replyMessageId, null));
    }

    @Override
    public Message sendAnimation(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(file), chatId, caption, replyMarkup, null, disableNotification));
    }

    @Override
    public Message sendAnimation(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(file), chatId, caption, replyMarkup, replyMessageId, disableNotification));
    }

    @Override
    public Message sendAnimation(InputStream stream, String filename) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(stream, filename), null, null, null, null, null));
    }

    @Override
    public Message sendAnimation(InputStream stream, String filename, Long chatId) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(stream, filename), chatId, null, null, null, null));
    }

    @Override
    public Message sendAnimation(InputStream stream, String filename, String caption) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(stream, filename), null, caption, null, null, null));
    }

    @Override
    public Message sendAnimation(InputStream stream, String filename, Integer replyMessageId) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(stream, filename), null, null, null, replyMessageId, null));
    }

    @Override
    public Message sendAnimation(InputStream stream, String filename, ReplyKeyboard replyMarkup) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(stream, filename), null, null, replyMarkup, null, null));
    }

    @Override
    public Message sendAnimation(InputStream stream, String filename, Boolean disableNotification) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(stream, filename), null, null, null, null, disableNotification));
    }

    @Override
    public Message sendAnimation(InputStream stream, String filename, Long chatId, String caption) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(stream, filename), chatId, caption, null, null, null));
    }

    @Override
    public Message sendAnimation(InputStream stream, String filename, Long chatId, Integer replyMessageId) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(stream, filename), chatId, null, null, replyMessageId, null));
    }

    @Override
    public Message sendAnimation(InputStream stream, String filename, Long chatId, ReplyKeyboard replyMarkup) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(stream, filename), chatId, null, replyMarkup, null, null));
    }

    @Override
    public Message sendAnimation(InputStream stream, String filename, Long chatId, Boolean disableNotification) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(stream, filename), chatId, null, null, null, disableNotification));
    }

    @Override
    public Message sendAnimation(InputStream stream, String filename, String caption, Integer replyMessageId) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(stream, filename), null, caption, null, replyMessageId, null));
    }

    @Override
    public Message sendAnimation(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(stream, filename), null, caption, replyMarkup, null, null));
    }

    @Override
    public Message sendAnimation(InputStream stream, String filename, String caption, Boolean disableNotification) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(stream, filename), null, caption, null, null, disableNotification));
    }

    @Override
    public Message sendAnimation(InputStream stream, String filename, Integer replyMessageId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(stream, filename), null, null, replyKeyboard, replyMessageId, null));
    }

    @Override
    public Message sendAnimation(InputStream stream, String filename, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(stream, filename), null, null, null, replyMessageId, disableNotification));
    }

    @Override
    public Message sendAnimation(InputStream stream, String filename, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(stream, filename), null, null, replyMarkup, null, disableNotification));
    }

    @Override
    public Message sendAnimation(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(stream, filename), chatId, caption, replyMarkup, null, null));
    }

    @Override
    public Message sendAnimation(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(stream, filename), null, caption, replyMarkup, replyMessageId, null));
    }

    @Override
    public Message sendAnimation(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(stream, filename), null, caption, replyMarkup, null, disableNotification));
    }

    @Override
    public Message sendAnimation(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(stream, filename), null, caption, replyMarkup, replyMessageId, disableNotification));
    }

    @Override
    public Message sendAnimation(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(stream, filename), chatId, caption, replyMarkup, replyMessageId, null));
    }

    @Override
    public Message sendAnimation(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(stream, filename), chatId, caption, replyMarkup, null, disableNotification));
    }

    @Override
    public Message sendAnimation(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendAnimation(sendAnimationBuilder(new InputFile(stream, filename), chatId, caption, replyMarkup, replyMessageId, disableNotification));
    }

    @Override
    public Message sendAnimation(SendAnimation animation) {
        notNull(animation, "animation param must not be null!");
        animation.setChatId(getChatId(Long.valueOf(animation.getChatId())));
        return tryExecuteSendAnimation(animation);
    }

    @Override
    public Boolean sendChatAction(String action) {
        return tryExecuteSendChatAction(sendChatActionBuilder(null, action, null));
    }

    @Override
    public Boolean sendChatAction(Long chatId, String action) {
        return tryExecuteSendChatAction(sendChatActionBuilder(chatId, action, null));
    }

    @Override
    public Boolean sendChatAction(Long chatId, String action, Integer messageThreadId) {
        return tryExecuteSendChatAction(sendChatActionBuilder(chatId, action, messageThreadId));
    }

    @Override
    public Boolean sendChatAction(SendChatAction sendChatAction) {
        notNull(sendChatAction, "sendChatAction param must not be null!");
        sendChatAction.setChatId(getChatId(Long.valueOf(sendChatAction.getChatId())));
        return tryExecuteSendChatAction(sendChatAction);
    }

    @Override
    public Message sendSticker(File file) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(file), null, null, null, null, null));
    }

    @Override
    public Message sendSticker(File file, Long chatId) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(file), chatId, null, null, null, null));
    }

    @Override
    public Message sendSticker(File file, String emoji) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(file), null, emoji, null, null, null));
    }

    @Override
    public Message sendSticker(File file, Integer replyMessageId) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(file), null, null, null, replyMessageId, null));
    }

    @Override
    public Message sendSticker(File file, ReplyKeyboard replyMarkup) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(file), null, null, replyMarkup, null, null));
    }

    @Override
    public Message sendSticker(File file, Boolean disableNotification) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(file), null, null, null, null, disableNotification));
    }

    @Override
    public Message sendSticker(File file, Long chatId, String emoji) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(file), chatId, emoji, null, null, null));
    }

    @Override
    public Message sendSticker(File file, Long chatId, Integer replyMessageId) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(file), chatId, null, null, replyMessageId, null));
    }

    @Override
    public Message sendSticker(File file, Long chatId, ReplyKeyboard replyMarkup) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(file), chatId, null, replyMarkup, null, null));
    }

    @Override
    public Message sendSticker(File file, Long chatId, Boolean disableNotification) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(file), chatId, null, null, null, disableNotification));
    }

    @Override
    public Message sendSticker(File file, String emoji, Integer replyMessageId) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(file), null, emoji, null, replyMessageId, null));
    }

    @Override
    public Message sendSticker(File file, String emoji, ReplyKeyboard replyMarkup) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(file), null, emoji, replyMarkup, null, null));
    }

    @Override
    public Message sendSticker(File file, String emoji, Boolean disableNotification) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(file), null, emoji, null, null, disableNotification));
    }

    @Override
    public Message sendSticker(File file, Integer replyMessageId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(file), null, null, replyKeyboard, replyMessageId, null));
    }

    @Override
    public Message sendSticker(File file, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(file), null, null, null, replyMessageId, disableNotification));
    }

    @Override
    public Message sendSticker(File file, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(file), null, null, replyMarkup, null, disableNotification));
    }

    @Override
    public Message sendSticker(File file, String emoji, ReplyKeyboard replyMarkup, Integer replyMessageId) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(file), null, emoji, replyMarkup, replyMessageId, null));
    }

    @Override
    public Message sendSticker(File file, String emoji, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(file), null, emoji, replyMarkup, null, disableNotification));
    }

    @Override
    public Message sendSticker(File file, String emoji, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(file), null, emoji, replyMarkup, replyMessageId, disableNotification));
    }

    @Override
    public Message sendSticker(File file, Long chatId, String emoji, ReplyKeyboard replyMarkup) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(file), chatId, emoji, replyMarkup, null, null));
    }

    @Override
    public Message sendSticker(File file, Long chatId, String emoji, ReplyKeyboard replyMarkup, Integer replyMessageId) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(file), chatId, emoji, replyMarkup, replyMessageId, null));
    }

    @Override
    public Message sendSticker(File file, Long chatId, String emoji, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(file), chatId, emoji, replyMarkup, null, disableNotification));
    }

    @Override
    public Message sendSticker(File file, Long chatId, String emoji, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(file), chatId, emoji, replyMarkup, replyMessageId, disableNotification));
    }

    @Override
    public Message sendSticker(InputStream stream, String filename) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(stream, filename), null, null, null, null, null));
    }

    @Override
    public Message sendSticker(InputStream stream, String filename, Long chatId) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(stream, filename), chatId, null, null, null, null));
    }

    @Override
    public Message sendSticker(InputStream stream, String filename, String emoji) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(stream, filename), null, emoji, null, null, null));
    }

    @Override
    public Message sendSticker(InputStream stream, String filename, Integer replyMessageId) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(stream, filename), null, null, null, replyMessageId, null));
    }

    @Override
    public Message sendSticker(InputStream stream, String filename, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(stream, filename), null, null, replyKeyboard, null, null));
    }

    @Override
    public Message sendSticker(InputStream stream, String filename, Boolean disableNotification) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(stream, filename), null, null, null, null, disableNotification));
    }

    @Override
    public Message sendSticker(InputStream stream, String filename, Long chatId, String emoji) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(stream, filename), chatId, emoji, null, null, null));
    }

    @Override
    public Message sendSticker(InputStream stream, String filename, Long chatId, Integer replyMessageId) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(stream, filename), chatId, null, null, replyMessageId, null));
    }

    @Override
    public Message sendSticker(InputStream stream, String filename, Long chatId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(stream, filename), chatId, null, replyKeyboard, null, null));
    }

    @Override
    public Message sendSticker(InputStream stream, String filename, Long chatId, Boolean disableNotification) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(stream, filename), chatId, null, null, null, disableNotification));
    }

    @Override
    public Message sendSticker(InputStream stream, String filename, String emoji, Integer replyMessageId) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(stream, filename), null, emoji, null, replyMessageId, null));
    }

    @Override
    public Message sendSticker(InputStream stream, String filename, String emoji, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(stream, filename), null, emoji, replyKeyboard, null, null));
    }

    @Override
    public Message sendSticker(InputStream stream, String filename, String emoji, Boolean disableNotification) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(stream, filename), null, emoji, null, null, disableNotification));
    }

    @Override
    public Message sendSticker(InputStream stream, String filename, Integer replyMessageId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(stream, filename), null, null, replyKeyboard, null, null));
    }

    @Override
    public Message sendSticker(InputStream stream, String filename, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(stream, filename), null, null, null, replyMessageId, disableNotification));
    }

    @Override
    public Message sendSticker(InputStream stream, String filename, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(stream, filename), null, null, replyMarkup, null, disableNotification));
    }

    @Override
    public Message sendSticker(InputStream stream, String filename, Long chatId, String emoji, ReplyKeyboard replyMarkup) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(stream, filename), chatId, emoji, replyMarkup, null, null));
    }

    @Override
    public Message sendSticker(InputStream stream, String filename, String emoji, ReplyKeyboard replyMarkup, Integer replyMessageId) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(stream, filename), null, emoji, replyMarkup, replyMessageId, null));
    }

    @Override
    public Message sendSticker(InputStream stream, String filename, String emoji, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(stream, filename), null, emoji, replyMarkup, null, disableNotification));
    }

    @Override
    public Message sendSticker(InputStream stream, String filename, String emoji, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(stream, filename), null, emoji, replyMarkup, replyMessageId, disableNotification));
    }

    @Override
    public Message sendSticker(InputStream stream, String filename, Long chatId, String emoji, ReplyKeyboard replyMarkup, Integer replyMessageId) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(stream, filename), chatId, emoji, replyMarkup, replyMessageId, null));
    }

    @Override
    public Message sendSticker(InputStream stream, String filename, Long chatId, String emoji, ReplyKeyboard replyMarkup, Boolean disableNotification) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(stream, filename), chatId, emoji, replyMarkup, null, disableNotification));
    }

    @Override
    public Message sendSticker(InputStream stream, String filename, Long chatId, String emoji, ReplyKeyboard replyMarkup, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendSticker(sendStickerBuilder(new InputFile(stream, filename), chatId, emoji, replyMarkup, replyMessageId, disableNotification));
    }

    @Override
    public Message sendSticker(SendSticker sticker) {
        notNull(sticker, "sticker param must not be null!");
        sticker.setChatId(getChatId(Long.valueOf(sticker.getChatId())));
        return tryExecuteSendSticker(sticker);
    }

    @Override
    public Message sendContact(String firstname, String phoneNumber) {
        return tryExecuteSendContact(sendContactBuilder(firstname, phoneNumber, null, null, null, null, null));
    }

    @Override
    public Message sendContact(String firstname, String phoneNumber, Long chatId) {
        return tryExecuteSendContact(sendContactBuilder(firstname, phoneNumber, chatId, null, null, null, null));
    }

    @Override
    public Message sendContact(String firstname, String phoneNumber, String lastname) {
        return tryExecuteSendContact(sendContactBuilder(firstname, phoneNumber, null, lastname, null, null, null));
    }

    @Override
    public Message sendContact(String firstname, String phoneNumber, Integer replyToMessageId) {
        return tryExecuteSendContact(sendContactBuilder(firstname, phoneNumber, null, null, null, replyToMessageId, null));
    }

    @Override
    public Message sendContact(String firstname, String phoneNumber, Boolean disableNotification) {
        return tryExecuteSendContact(sendContactBuilder(firstname, phoneNumber, null, null, null, null, disableNotification));
    }

    @Override
    public Message sendContact(String firstname, String phoneNumber, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendContact(sendContactBuilder(firstname, phoneNumber, null, null, replyKeyboard, null, null));
    }

    @Override
    public Message sendContact(String firstname, String phoneNumber, Long chatId, String lastname) {
        return tryExecuteSendContact(sendContactBuilder(firstname, phoneNumber, chatId, lastname, null, null, null));
    }

    @Override
    public Message sendContact(String firstname, String phoneNumber, Long chatId, Integer replyToMessageId) {
        return tryExecuteSendContact(sendContactBuilder(firstname, phoneNumber, chatId, null, null, replyToMessageId, null));
    }

    @Override
    public Message sendContact(String firstname, String phoneNumber, Long chatId, Boolean disableNotification) {
        return tryExecuteSendContact(sendContactBuilder(firstname, phoneNumber, chatId, null, null, null, disableNotification));
    }

    @Override
    public Message sendContact(String firstname, String phoneNumber, Long chatId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendContact(sendContactBuilder(firstname, phoneNumber, chatId, null, replyKeyboard, null, null));
    }

    @Override
    public Message sendContact(String firstname, String phoneNumber, String lastname, Integer replyToMessageId) {
        return tryExecuteSendContact(sendContactBuilder(firstname, phoneNumber, null, lastname, null, replyToMessageId, null));
    }

    @Override
    public Message sendContact(String firstname, String phoneNumber, String lastname, Boolean disableNotification) {
        return tryExecuteSendContact(sendContactBuilder(firstname, phoneNumber, null, lastname, null, null, disableNotification));
    }

    @Override
    public Message sendContact(String firstname, String phoneNumber, String lastname, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendContact(sendContactBuilder(firstname, phoneNumber, null, lastname, replyKeyboard, null, null));
    }

    @Override
    public Message sendContact(String firstname, String phoneNumber, Integer replyToMessageId, Boolean disableNotification) {
        return tryExecuteSendContact(sendContactBuilder(firstname, phoneNumber, null, null, null, replyToMessageId, disableNotification));
    }

    @Override
    public Message sendContact(String firstname, String phoneNumber, Integer replyToMessageId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendContact(sendContactBuilder(firstname, phoneNumber, null, null, replyKeyboard, replyToMessageId, null));
    }

    @Override
    public Message sendContact(String firstname, String phoneNumber, Boolean disableNotification, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendContact(sendContactBuilder(firstname, phoneNumber, null, null, replyKeyboard, null, disableNotification));
    }

    @Override
    public Message sendContact(String firstname, String phoneNumber, String lastname, Integer replyToMessageId, Boolean disableNotification) {
        return tryExecuteSendContact(sendContactBuilder(firstname, phoneNumber, null, lastname, null, replyToMessageId, disableNotification));
    }

    @Override
    public Message sendContact(String firstname, String phoneNumber, String lastname, Integer replyToMessageId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendContact(sendContactBuilder(firstname, phoneNumber, null, lastname, replyKeyboard, replyToMessageId, null));
    }

    @Override
    public Message sendContact(String firstname, String phoneNumber, Long chatId, String lastname, Integer replyToMessageId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendContact(sendContactBuilder(firstname, phoneNumber, chatId, lastname, replyKeyboard, replyToMessageId, null));
    }

    @Override
    public Message sendContact(String firstname, String phoneNumber, Long chatId, String lastname, Integer replyToMessageId, Boolean disableNotification) {
        return tryExecuteSendContact(sendContactBuilder(firstname, phoneNumber, chatId, lastname, null, replyToMessageId, disableNotification));
    }

    @Override
    public Message sendContact(String firstname, String phoneNumber, Long chatId, String lastname, ReplyKeyboard replyKeyboard, Boolean disableNotification) {
        return tryExecuteSendContact(sendContactBuilder(firstname, phoneNumber, chatId, lastname, replyKeyboard, null, disableNotification));
    }

    @Override
    public Message sendContact(String firstname, String phoneNumber, String lastname, Integer replyToMessageId, Boolean disableNotification, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendContact(sendContactBuilder(firstname, phoneNumber, null, lastname, replyKeyboard, replyToMessageId, disableNotification));
    }

    @Override
    public Message sendContact(String firstname, String phoneNumber, Long chatId, Integer replyToMessageId, Boolean disableNotification, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendContact(sendContactBuilder(firstname, phoneNumber, chatId, null, replyKeyboard, replyToMessageId, disableNotification));
    }

    @Override
    public Message sendContact(SendContact contact) {
        notNull(contact, "contact param must not be null!");
        contact.setChatId(getChatId(Long.valueOf(contact.getChatId())));
        return tryExecuteSendContact(contact);
    }

    @Override
    public Message sendGame(String gameShortName) {
        return tryExecuteBotMethod(sendGameBuilder(gameShortName, null, null, null));
    }

    @Override
    public Message sendGame(String gameShortName, Long chatId) {
        return tryExecuteBotMethod(sendGameBuilder(gameShortName, chatId, null, null));
    }

    @Override
    public Message sendGame(String gameShortName, Integer replyToMessageId) {
        return tryExecuteBotMethod(sendGameBuilder(gameShortName, null, replyToMessageId, null));
    }

    @Override
    public Message sendGame(String gameShortName, ReplyKeyboard replyKeyboard) {
        return tryExecuteBotMethod(sendGameBuilder(gameShortName, null, null, replyKeyboard));
    }

    @Override
    public Message sendGame(String gameShortName, Long chatId, Integer replyToMessageId) {
        return tryExecuteBotMethod(sendGameBuilder(gameShortName, chatId, replyToMessageId, null));
    }

    @Override
    public Message sendGame(String gameShortName, Long chatId, ReplyKeyboard replyKeyboard) {
        return tryExecuteBotMethod(sendGameBuilder(gameShortName, chatId, null, replyKeyboard));
    }

    @Override
    public Message sendGame(String gameShortName, Integer replyToMessageId, ReplyKeyboard replyKeyboard) {
        return tryExecuteBotMethod(sendGameBuilder(gameShortName, null, replyToMessageId, replyKeyboard));
    }

    @Override
    public Message sendGame(String gameShortName, Long chatId, Integer replyToMessageId, ReplyKeyboard replyKeyboard) {
        return tryExecuteBotMethod(sendGameBuilder(gameShortName, chatId, replyToMessageId, replyKeyboard));
    }

    @Override
    public Message sendGame(SendGame game) {
        notNull(game, "game param must not be null!");
        game.setChatId(getChatId(Long.valueOf(game.getChatId())));
        return tryExecuteBotMethod(game);
    }

    @Override
    public Message sendLocation(Round round) {
        return tryExecuteBotMethod(sendLocationBuilder(round, null, null, null));
    }

    @Override
    public Message sendLocation(Round round, Long chatId) {
        return tryExecuteBotMethod(sendLocationBuilder(round, chatId, null, null));
    }

    @Override
    public Message sendLocation(Round round, Integer replyToMessageId) {
        return tryExecuteBotMethod(sendLocationBuilder(round, null, replyToMessageId, null));
    }

    @Override
    public Message sendLocation(Round round, ReplyKeyboard replyMarkup) {
        return tryExecuteBotMethod(sendLocationBuilder(round, null, null, replyMarkup));
    }

    @Override
    public Message sendLocation(Round round, Long chatId, Integer replyToMessageId) {
        return tryExecuteBotMethod(sendLocationBuilder(round, chatId, replyToMessageId, null));
    }

    @Override
    public Message sendLocation(Round round, Long chatId, ReplyKeyboard replyMarkup) {
        return tryExecuteBotMethod(sendLocationBuilder(round, chatId, null, replyMarkup));
    }

    @Override
    public Message sendLocation(Round round, Integer replyToMessageId, ReplyKeyboard replyMarkup) {
        return tryExecuteBotMethod(sendLocationBuilder(round, null, replyToMessageId, replyMarkup));
    }

    @Override
    public Message sendLocation(Round round, Long chatId, Integer replyToMessageId, ReplyKeyboard replyMarkup) {
        return tryExecuteBotMethod(sendLocationBuilder(round, chatId, replyToMessageId, replyMarkup));
    }

    @Override
    public Message sendLocation(SendLocation location) {
        notNull(location, "location param must not be null!");
        location.setChatId(getChatId(Long.valueOf(location.getChatId())));
        return tryExecuteBotMethod(location);
    }

    @Override
    public List<Message> sendMediaGroup(List<InputMedia> medias) {
        return tryExecuteSendMediaGroup(sendMediaGroupBuilder(medias, null, null, null));
    }

    @Override
    public List<Message> sendMediaGroup(List<InputMedia> medias, Long chatId) {
        return tryExecuteSendMediaGroup(sendMediaGroupBuilder(medias, chatId, null, null));
    }

    @Override
    public List<Message> sendMediaGroup(List<InputMedia> medias, Integer replyMessageId) {
        return tryExecuteSendMediaGroup(sendMediaGroupBuilder(medias, null, replyMessageId, null));
    }

    @Override
    public List<Message> sendMediaGroup(List<InputMedia> medias, Boolean disableNotification) {
        return tryExecuteSendMediaGroup(sendMediaGroupBuilder(medias, null, null, disableNotification));
    }

    @Override
    public List<Message> sendMediaGroup(List<InputMedia> medias, Long chatId, Integer replyMessageId) {
        return tryExecuteSendMediaGroup(sendMediaGroupBuilder(medias, chatId, replyMessageId, null));
    }

    @Override
    public List<Message> sendMediaGroup(List<InputMedia> medias, Long chatId, Boolean disableNotification) {
        return tryExecuteSendMediaGroup(sendMediaGroupBuilder(medias, chatId, null, disableNotification));
    }

    @Override
    public List<Message> sendMediaGroup(List<InputMedia> medias, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendMediaGroup(sendMediaGroupBuilder(medias, null, replyMessageId, disableNotification));
    }

    @Override
    public List<Message> sendMediaGroup(List<InputMedia> medias, Long chatId, Integer replyMessageId, Boolean disableNotification) {
        return tryExecuteSendMediaGroup(sendMediaGroupBuilder(medias, chatId, replyMessageId, disableNotification));
    }

    @Override
    public List<Message> sendMediaGroup(SendMediaGroup mediaGroup) {
        notNull(mediaGroup, "mediaGroup param must not be null!");
        mediaGroup.setChatId(getChatId(Long.valueOf(mediaGroup.getChatId())));
        return tryExecuteSendMediaGroup(mediaGroup);
    }

    @Override
    public Boolean pinChatMessage(Long chatId, Integer messageId) {
        return tryPinChatMessage(pinChatMessageBuilder(chatId, messageId, null));
    }

    @Override
    public Boolean pinChatMessage(Long chatId, Integer messageId, Boolean disableNotification) {
        return tryPinChatMessage(pinChatMessageBuilder(chatId, messageId, disableNotification));
    }

    @Override
    public Boolean pinChatMessage(PinChatMessage pinChatMessage) {
        notNull(pinChatMessage, "pinChatMessage param must not be null!");
        pinChatMessage.setChatId(getChatId(Long.valueOf(pinChatMessage.getChatId())));
        return tryPinChatMessage(pinChatMessage);
    }

    @Override
    public Boolean unpinAllChatMessages(Long chatId) {
        return tryUnpinAllChatMessages(unpinAllChatMessageBuilder(chatId));
    }

    @Override
    public Boolean unpinAllChatMessages(UnpinAllChatMessages unpinAllChatMessages) {
        notNull(unpinAllChatMessages, "pinAllChatMessage param must not be null!");
        unpinAllChatMessages.setChatId(getChatId(Long.valueOf(unpinAllChatMessages.getChatId())));
        return tryPinChatMessage(unpinAllChatMessages);
    }

    @Override
    public Boolean unpinChatMessage(Long chatId, Integer messageId) {
        return tryUnpinChatMessage(unpinChatMessageBuilder(chatId, messageId));
    }

    @Override
    public Boolean unpinChatMessage(UnpinChatMessage unpinChatMessage) {
        notNull(unpinChatMessage, "unpinChatMessage param must not be null!");
        unpinChatMessage.setChatId(getChatId(Long.valueOf(unpinChatMessage.getChatId())));
        return tryPinChatMessage(unpinChatMessage);
    }

    protected PinChatMessage pinChatMessageBuilder(Long chatId, Integer messageId, Boolean disableNotification) {
        return PinChatMessage.builder()
                .chatId(chatId)
                .messageId(messageId)
                .disableNotification(disableNotification)
                .build();
    }

    protected UnpinChatMessage unpinChatMessageBuilder(Long chatId, Integer messageId) {
        return UnpinChatMessage.builder()
                .chatId(chatId)
                .messageId(messageId)
                .build();
    }

    protected UnpinAllChatMessages unpinAllChatMessageBuilder(Long chatId) {
        return UnpinAllChatMessages.builder()
                .chatId(chatId)
                .build();
    }

    protected SendMediaGroup sendMediaGroupBuilder(List<InputMedia> medias, Long chatId, Integer replyMessageId, Boolean disableNotification) {
        return SendMediaGroup.builder()
                .medias(medias)
                .chatId(getChatId(chatId))
                .replyToMessageId(replyMessageId)
                .disableNotification(disableNotification)
                .build();
    }

    protected SendGame sendGameBuilder(String gameShortName, Long chatId, Integer replyToMessageId, ReplyKeyboard replyKeyboard) {
        return SendGame.builder()
                .chatId(getChatId(chatId))
                .replyMarkup(replyKeyboard)
                .gameShortName(gameShortName)
                .replyToMessageId(replyToMessageId)
                .build();
    }

    protected SendLocation sendLocationBuilder(Round round, Long chatId, Integer replyToMessageId, ReplyKeyboard replyMarkup) {
        return SendLocation.builder()
                .chatId(getChatId(chatId))
                .replyToMessageId(replyToMessageId)
                .longitude(round.getLongitude())
                .latitude(round.getLatitude())
                .replyMarkup(replyMarkup)
                .build();
    }

    protected AnswerInlineQuery answerInlineQueryBuilder(List<InlineQueryResult> results, String inlineQueryId, Integer cacheTime, Boolean isPersonal) {
        return AnswerInlineQuery.builder()
                .inlineQueryId(getInlineQueryId(inlineQueryId))
                .isPersonal(isPersonal)
                .cacheTime(cacheTime)
                .results(results)
                .build();
    }

    protected AnswerCallbackQuery answerCallbackQueryBuilder(String text, String callbackQueryId, String url, Boolean showAlert) {
        return AnswerCallbackQuery.builder()
                .text(text)
                .url(url)
                .showAlert(showAlert)
                .callbackQueryId(getCallbackQueryId(callbackQueryId))
                .build();
    }

    protected SendDice sendDiceBuilder(String emoji, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard) {
        return SendDice.builder()
                .emoji(emoji)
                .chatId(getChatId(chatId))
                .replyToMessageId(replyMessageId)
                .replyMarkup(replyKeyboard)
                .build();
    }

    protected SendPhoto sendPhotoBuilder(InputFile inputFile, Long chatId, String caption, ReplyKeyboard replyKeyboard, Integer replyMessageId, Boolean disableNotification) {
        return SendPhoto.builder()
                .photo(inputFile)
                .chatId(getChatId(chatId))
                .caption(caption)
                .replyMarkup(replyKeyboard)
                .replyToMessageId(replyMessageId)
                .disableNotification(disableNotification)
                .build();
    }

    protected SendContact sendContactBuilder(String firstname, String phoneNumber, Long chatId, String lastname, ReplyKeyboard replyKeyboard, Integer replyMessageId, Boolean disableNotification) {
        return SendContact.builder()
                .phoneNumber(phoneNumber)
                .firstName(firstname)
                .lastName(lastname)
                .chatId(getChatId(chatId))
                .replyToMessageId(replyMessageId)
                .replyMarkup(replyKeyboard)
                .disableNotification(disableNotification)
                .build();
    }

    protected SendSticker sendStickerBuilder(InputFile inputFile, Long chatId, String emoji, ReplyKeyboard replyKeyboard, Integer replyMessageId, Boolean disableNotification) {
        return SendSticker.builder()
                .sticker(inputFile)
                .chatId(getChatId(chatId))
                .emoji(emoji)
                .replyToMessageId(replyMessageId)
                .replyMarkup(replyKeyboard)
                .disableNotification(disableNotification)
                .build();
    }

    protected SendChatAction sendChatActionBuilder(Long chatId, String action, Integer messageThreadId) {
        return SendChatAction.builder()
                .chatId(getChatId(chatId))
                .action(action)
                .messageThreadId(messageThreadId)
                .build();
    }

    protected SendAnimation sendAnimationBuilder(InputFile inputFile, Long chatId, String caption, ReplyKeyboard replyKeyboard, Integer replyMessageId, Boolean disableNotification) {
        return SendAnimation.builder()
                .animation(inputFile)
                .chatId(getChatId(chatId))
                .caption(caption)
                .replyToMessageId(replyMessageId)
                .replyMarkup(replyKeyboard)
                .disableNotification(disableNotification)
                .build();
    }

    protected SendAudio sendAudioBuilder(InputFile inputFile, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard, String caption, String title, Boolean disableNotification) {
        return SendAudio.builder()
                .audio(inputFile)
                .chatId(getChatId(chatId))
                .disableNotification(disableNotification)
                .replyToMessageId(replyMessageId)
                .replyMarkup(replyKeyboard)
                .caption(caption)
                .title(title)
                .build();
    }

    protected SendDocument sendDocumentBuilder(InputFile inputFile, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard, String caption, Boolean disableNotification) {
        return SendDocument.builder()
                .caption(caption)
                .document(inputFile)
                .replyMarkup(replyKeyboard)
                .replyToMessageId(replyMessageId)
                .chatId(getChatId(chatId))
                .disableNotification(disableNotification)
                .build();
    }

    protected SendMessage sendMessageBuilder(String text, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard, String parseMode, Boolean disableNotification) {
        return SendMessage.builder()
                .text(text)
                .parseMode(parseMode)
                .replyMarkup(replyKeyboard)
                .replyToMessageId(replyMessageId)
                .chatId(getChatId(chatId))
                .disableNotification(disableNotification)
                .build();
    }

    protected ForwardMessage forwardMessageBuilder(String fromChatId, Long chatId, Integer messageId) {
        return ForwardMessage.builder()
                .messageId(messageId)
                .fromChatId(fromChatId)
                .chatId(getChatId(chatId))
                .build();
    }

    protected SendVideo sendVideoBuilder(InputFile stream, Long chatId, String caption, ReplyKeyboard replyKeyboard, Integer replyMessageId) {
        return SendVideo.builder()
                .caption(caption)
                .video(stream)
                .replyMarkup(replyKeyboard)
                .replyToMessageId(replyMessageId)
                .chatId(getChatId(chatId))
                .build();
    }

    protected SendVideoNote sendVideoNoteBuilder(InputFile stream, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard) {
        return SendVideoNote.builder()
                .videoNote(stream)
                .replyMarkup(replyKeyboard)
                .replyToMessageId(replyMessageId)
                .chatId(getChatId(chatId))
                .build();
    }

    protected SendVoice sendVoiceBuilder(InputFile stream, Long chatId, String caption, ReplyKeyboard replyKeyboard, Integer replyMessageId) {
        return SendVoice.builder()
                .caption(caption)
                .voice(stream)
                .replyMarkup(replyKeyboard)
                .replyToMessageId(replyMessageId)
                .chatId(getChatId(chatId))
                .build();
    }

    protected Long getChatId(Long chatId) {
        return Objects.isNull(chatId) ? getCurrentUpdateChatId() : chatId;
    }

    @NotNull
    private Long getCurrentUpdateChatId() {
        Long currentUpdateChatId = UpdateUtils.getChatId(getUpdate());
        return checkParamIsNullThenThrowException(currentUpdateChatId, "Current chat id is null!");
    }

    protected String getCallbackQueryId(String callbackId) {
        return Objects.isNull(callbackId) ? getCurrentUpdateCallbackId() : callbackId;
    }

    @NotNull
    private String getCurrentUpdateCallbackId() {
        String currentUpdateCallbackId = UpdateUtils.getCallbackId(getUpdate());
        return checkParamIsNullThenThrowException(currentUpdateCallbackId, "Current callback id is null!");
    }

    protected String getInlineQueryId(String inlineQueryId) {
        return Objects.isNull(inlineQueryId) ? getCurrentUpdateInlineQueryId() : inlineQueryId;
    }

    @NotNull
    private String getCurrentUpdateInlineQueryId() {
        String currentUpdateInlineQueryId = UpdateUtils.getInlineQueryId(getUpdate());
        return checkParamIsNullThenThrowException(currentUpdateInlineQueryId, "Current inline query id is null!");
    }

    @NotNull
    private static <T> T checkParamIsNullThenThrowException(T param, String message) {
        if (Objects.isNull(param)) {
            throw new RuntimeException(message);
        }
        return param;
    }

    protected List<Message> tryExecuteSendMediaGroup(SendMediaGroup mediaGroup) {
        try {
            return getInstance().execute(mediaGroup);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    protected Boolean tryUnpinChatMessage(BotApiMethodBoolean apiMethodBoolean) {
        try {
            return getInstance().execute(apiMethodBoolean);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    protected Boolean tryPinChatMessage(BotApiMethodBoolean apiMethodBoolean) {
        try {
            return getInstance().execute(apiMethodBoolean);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    protected Boolean tryUnpinAllChatMessages(BotApiMethodBoolean apiMethodBoolean) {
        try {
            return getInstance().execute(apiMethodBoolean);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    protected Message tryExecuteBotMethod(BotApiMethodMessage message) {
        try {
            return getInstance().execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    protected Boolean tryExecuteAnswerQueryMethod(BotApiMethodBoolean apiMethodBoolean) {
        try {
            return getInstance().execute(apiMethodBoolean);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    protected Message tryExecuteSendPhoto(SendPhoto photo) {
        try {
            return getInstance().execute(photo);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    protected Message tryExecuteSendContact(SendContact sendContact) {
        try {
            return getInstance().execute(sendContact);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    protected Message tryExecuteSendSticker(SendSticker sendSticker) {
        try {
            return getInstance().execute(sendSticker);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    protected Boolean tryExecuteSendChatAction(SendChatAction sendChatAction) {
        try {
            return getInstance().execute(sendChatAction);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    protected Message tryExecuteSendAnimation(SendAnimation sendAnimation) {
        try {
            return getInstance().execute(sendAnimation);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    protected Message tryExecuteSendAudio(SendAudio sendAudio) {
        try {
            return getInstance().execute(sendAudio);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    protected Message tryExecuteSendDocument(SendDocument sendDocument) {
        try {
            return getInstance().execute(sendDocument);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    protected Message tryExecuteSendVideo(SendVideo sendVideo) {
        try {
            return getInstance().execute(sendVideo);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    protected Message tryExecuteSendVideoNote(SendVideoNote sendVideoNote) {
        try {
            return getInstance().execute(sendVideoNote);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    protected Message tryExecuteSendVoice(SendVoice sendVoice) {
        try {
            return getInstance().execute(sendVoice);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    protected Update getUpdate() {
        return getCurrentUpdate();
    }

    protected FluentBot getInstance() {
        if (Objects.isNull(this.instance)) {
            this.instance = singletonBot.getInstance();
        }
        return this.instance;
    }
}
