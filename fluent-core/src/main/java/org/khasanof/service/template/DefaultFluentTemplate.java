package org.khasanof.service.template;

import org.khasanof.FluentBot;
import org.khasanof.context.FluentContextHolder;
import org.khasanof.context.singleton.GenericSingleton;
import org.khasanof.utils.UpdateUtils;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.service.template
 * @since 1/23/2024 9:28 PM
 */
public class DefaultFluentTemplate implements FluentTemplate {

    private final GenericSingleton<FluentBot> singletonBot;

    public DefaultFluentTemplate(GenericSingleton<FluentBot> singletonBot) {
        this.singletonBot = singletonBot;
    }

    @Override
    public Message sendText(String text) {
        return tryExecuteBotMethod(sendMessageBuilder(text, null, null, null));
    }

    @Override
    public Message sendText(String text, Long chatId) {
        return tryExecuteBotMethod(sendMessageBuilder(text, chatId, null, null));
    }

    @Override
    public Message sendText(String text, Integer replyMessageId) {
        return tryExecuteBotMethod(sendMessageBuilder(text, null, replyMessageId, null));
    }

    @Override
    public Message sendText(String text, ReplyKeyboard replyKeyboard) {
        return tryExecuteBotMethod(sendMessageBuilder(text, null, null, replyKeyboard));
    }

    @Override
    public Message sendText(String text, Long chatId, Integer replyMessageId) {
        return tryExecuteBotMethod(sendMessageBuilder(text, chatId, replyMessageId, null));
    }

    @Override
    public Message sendText(String text, Long chatId, ReplyKeyboard replyKeyboard) {
        return tryExecuteBotMethod(sendMessageBuilder(text, chatId, null, replyKeyboard));
    }

    @Override
    public Message sendDocument(File file) {
        return tryExecuteBotMethod(sendDocumentBuilder(new InputFile(file), null, null, null, null));
    }

    @Override
    public Message sendDocument(File file, Long chatId) {
        return tryExecuteBotMethod(sendDocumentBuilder(new InputFile(file), chatId, null, null, null));
    }

    @Override
    public Message sendDocument(File file, ReplyKeyboard replyKeyboard) {
        return tryExecuteBotMethod(sendDocumentBuilder(new InputFile(file), null, null, replyKeyboard, null));
    }

    @Override
    public Message sendDocument(File file, String caption) {
        return tryExecuteBotMethod(sendDocumentBuilder(new InputFile(file), null, null, null, caption));
    }

    @Override
    public Message sendDocument(File file, Integer replyMessageId) {
        return tryExecuteBotMethod(sendDocumentBuilder(new InputFile(file), null, replyMessageId, null, null));
    }

    @Override
    public Message sendDocument(File file, Long chatId, String caption) {
        return tryExecuteBotMethod(sendDocumentBuilder(new InputFile(file), chatId, null, null, caption));
    }

    @Override
    public Message sendDocument(File file, Long chatId, Integer replyMessageId) {
        return tryExecuteBotMethod(sendDocumentBuilder(new InputFile(file), chatId, replyMessageId, null, null));
    }

    @Override
    public Message sendDocument(File file, Long chatId, ReplyKeyboard replyKeyboard) {
        return tryExecuteBotMethod(sendDocumentBuilder(new InputFile(file), chatId, null, replyKeyboard, null));
    }

    @Override
    public Message sendDocument(File file, Long chatId, ReplyKeyboard replyKeyboard, String caption) {
        return tryExecuteBotMethod(sendDocumentBuilder(new InputFile(file), chatId, null, replyKeyboard, caption));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename) {
        return tryExecuteBotMethod(sendDocumentBuilder(new InputFile(inputStream, filename), null, null, null, null));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename, Long chatId) {
        return tryExecuteBotMethod(sendDocumentBuilder(new InputFile(inputStream, filename), chatId, null, null, null));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename, String caption) {
        return tryExecuteBotMethod(sendDocumentBuilder(new InputFile(inputStream, filename), null, null, null, caption));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename, Integer replyMessageId) {
        return tryExecuteBotMethod(sendDocumentBuilder(new InputFile(inputStream, filename), null, replyMessageId, null, null));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename, ReplyKeyboard replyKeyboard) {
        return tryExecuteBotMethod(sendDocumentBuilder(new InputFile(inputStream, filename), null, null, replyKeyboard, null));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename, Long chatId, String caption) {
        return tryExecuteBotMethod(sendDocumentBuilder(new InputFile(inputStream, filename), chatId, null, null, caption));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename, Long chatId, Integer replyMessageId) {
        return tryExecuteBotMethod(sendDocumentBuilder(new InputFile(inputStream, filename), chatId, replyMessageId, null, null));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename, Long chatId, ReplyKeyboard replyKeyboard) {
        return tryExecuteBotMethod(sendDocumentBuilder(new InputFile(inputStream, filename), chatId, null, replyKeyboard, null));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename, Long chatId, ReplyKeyboard replyKeyboard, String caption) {
        return tryExecuteBotMethod(sendDocumentBuilder(new InputFile(inputStream, filename), chatId, null, replyKeyboard, caption));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard, String caption) {
        return tryExecuteBotMethod(sendDocumentBuilder(new InputFile(inputStream, filename), chatId, replyMessageId, replyKeyboard, caption));
    }

    private SendDocument sendDocumentBuilder(InputFile inputFile, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard, String caption) {
        return SendDocument.builder()
                .caption(caption)
                .document(inputFile)
                .replyMarkup(replyKeyboard)
                .replyToMessageId(replyMessageId)
                .chatId(String.valueOf(getChatId(chatId)))
                .build();
    }

    private SendMessage sendMessageBuilder(String text, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard) {
        return SendMessage.builder()
                .text(text)
                .replyMarkup(replyKeyboard)
                .replyToMessageId(replyMessageId)
                .chatId(String.valueOf(getChatId(chatId)))
                .build();
    }

    private Long getChatId(Long chatId) {
        return Objects.isNull(chatId) ? UpdateUtils.getChatIdFromUpdate(FluentContextHolder.getCurrentUpdate().getUpdate()) : chatId;
    }

    private Message tryExecuteBotMethod(BotApiMethodMessage message) {
        try {
            return getInstance().execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private Message tryExecuteBotMethod(SendDocument sendDocument) {
        try {
            return getInstance().execute(sendDocument);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private FluentBot getInstance() {
        return singletonBot.getInstance();
    }
}
