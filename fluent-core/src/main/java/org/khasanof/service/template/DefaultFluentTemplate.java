package org.khasanof.service.template;

import org.khasanof.FluentBot;
import org.khasanof.context.FluentContextHolder;
import org.khasanof.context.singleton.GenericSingleton;
import org.khasanof.utils.UpdateUtils;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.InputStream;
import java.util.Objects;

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
    public Message sendTextWithParseMode(String text, String parseMode) {
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
        sendDice.setChatId(getChatId(Long.valueOf(sendDice.getChatId())));
        return tryExecuteBotMethod(sendDice);
    }

    @Override
    public Boolean sendAnswerCallbackQuery(String text) {
        return tryExecuteAnswerCallbackQuery(answerCallbackQueryBuilder(text, null, null, null));
    }

    @Override
    public Boolean sendAnswerCallbackQuery(String text, String callbackQueryId) {
        return tryExecuteAnswerCallbackQuery(answerCallbackQueryBuilder(text, callbackQueryId, null, null));
    }

    @Override
    public Boolean sendAnswerCallbackQuery(String text, Boolean showAlert) {
        return tryExecuteAnswerCallbackQuery(answerCallbackQueryBuilder(text, null, null, showAlert));
    }

    @Override
    public Boolean sendAnswerCallbackQuery(String text, String callbackQueryId, String url) {
        return tryExecuteAnswerCallbackQuery(answerCallbackQueryBuilder(text, text, url, null));
    }

    @Override
    public Boolean sendAnswerCallbackQuery(String text, String callbackQueryId, Boolean showAlert) {
        return tryExecuteAnswerCallbackQuery(answerCallbackQueryBuilder(text, callbackQueryId, null, showAlert));
    }

    @Override
    public Boolean sendAnswerCallbackQuery(String text, String callbackQueryId, String url, Boolean showAlert) {
        return tryExecuteAnswerCallbackQuery(answerCallbackQueryBuilder(text, callbackQueryId, url, showAlert));
    }

    @Override
    public Boolean sendAnswerCallbackQuery(AnswerCallbackQuery answerCallbackQuery) {
        answerCallbackQuery.setCallbackQueryId(getCallbackQueryId(answerCallbackQuery.getCallbackQueryId()));
        return tryExecuteAnswerCallbackQuery(answerCallbackQuery);
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
                .chatId(String.valueOf(getChatId(chatId)))
                .disableNotification(disableNotification)
                .build();
    }

    protected SendMessage sendMessageBuilder(String text, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard, String parseMode, Boolean disableNotification) {
        return SendMessage.builder()
                .text(text)
                .parseMode(parseMode)
                .replyMarkup(replyKeyboard)
                .replyToMessageId(replyMessageId)
                .chatId(String.valueOf(getChatId(chatId)))
                .disableNotification(disableNotification)
                .build();
    }

    protected Long getChatId(Long chatId) {
        return Objects.isNull(chatId) ? UpdateUtils.getChatIdFromUpdate(getUpdate()) : chatId;
    }

    protected String getCallbackQueryId(String callbackId) {
        return Objects.isNull(callbackId) ? getUpdate().getCallbackQuery().getId() : callbackId;
    }

    protected Message tryExecuteBotMethod(BotApiMethodMessage message) {
        try {
            return getInstance().execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    protected Boolean tryExecuteAnswerCallbackQuery(AnswerCallbackQuery answerCallbackQuery) {
        try {
            return getInstance().execute(answerCallbackQuery);
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

    protected Update getUpdate() {
        return FluentContextHolder.getCurrentUpdate().getUpdate();
    }

    protected FluentBot getInstance() {
        if (Objects.isNull(this.instance)) {
            this.instance = singletonBot.getInstance();
        }
        return this.instance;
    }
}
