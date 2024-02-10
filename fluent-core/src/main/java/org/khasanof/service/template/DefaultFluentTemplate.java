package org.khasanof.service.template;

import org.khasanof.FluentBot;
import org.khasanof.context.FluentContextHolder;
import org.khasanof.context.singleton.GenericSingleton;
import org.khasanof.utils.UpdateUtils;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendDice;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
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

    private FluentBot instance;
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
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), null, null, null, null));
    }

    @Override
    public Message sendDocument(File file, Long chatId) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), chatId, null, null, null));
    }

    @Override
    public Message sendDocument(File file, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), null, null, replyKeyboard, null));
    }

    @Override
    public Message sendDocument(File file, String caption) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), null, null, null, caption));
    }

    @Override
    public Message sendDocument(File file, Integer replyMessageId) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), null, replyMessageId, null, null));
    }

    @Override
    public Message sendDocument(File file, Long chatId, String caption) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), chatId, null, null, caption));
    }

    @Override
    public Message sendDocument(File file, Long chatId, Integer replyMessageId) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), chatId, replyMessageId, null, null));
    }

    @Override
    public Message sendDocument(File file, Long chatId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), chatId, null, replyKeyboard, null));
    }

    @Override
    public Message sendDocument(File file, Long chatId, ReplyKeyboard replyKeyboard, String caption) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(file), chatId, null, replyKeyboard, caption));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(inputStream, filename), null, null, null, null));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename, Long chatId) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(inputStream, filename), chatId, null, null, null));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename, String caption) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(inputStream, filename), null, null, null, caption));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename, Integer replyMessageId) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(inputStream, filename), null, replyMessageId, null, null));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(inputStream, filename), null, null, replyKeyboard, null));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename, Long chatId, String caption) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(inputStream, filename), chatId, null, null, caption));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename, Long chatId, Integer replyMessageId) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(inputStream, filename), chatId, replyMessageId, null, null));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename, Long chatId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(inputStream, filename), chatId, null, replyKeyboard, null));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename, Long chatId, ReplyKeyboard replyKeyboard, String caption) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(inputStream, filename), chatId, null, replyKeyboard, caption));
    }

    @Override
    public Message sendDocument(InputStream inputStream, String filename, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard, String caption) {
        return tryExecuteSendDocument(sendDocumentBuilder(new InputFile(inputStream, filename), chatId, replyMessageId, replyKeyboard, caption));
    }

    @Override
    public Message sendAudio(File file) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), null, null, null, null, null));
    }

    @Override
    public Message sendAudio(File file, Long chatId) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), chatId, null, null, null, null));
    }

    @Override
    public Message sendAudio(File file, String caption) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), null, null, null, caption, null));
    }

    @Override
    public Message sendAudio(File file, Integer replyMessageId) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), null, replyMessageId, null, null, null));
    }

    @Override
    public Message sendAudio(File file, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), null, null, replyKeyboard, null, null));
    }

    @Override
    public Message sendAudio(File file, Long chatId, String caption) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), chatId, null, null, caption, null));
    }

    @Override
    public Message sendAudio(File file, Long chatId, Integer replyMessageId) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), null, replyMessageId, null, null, null));
    }

    @Override
    public Message sendAudio(File file, Long chatId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), chatId, null, replyKeyboard, null, null));
    }

    @Override
    public Message sendAudio(File file, String caption, Integer replyMessageId) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), null, replyMessageId, null, caption, null));
    }

    @Override
    public Message sendAudio(File file, String caption, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), null, null, replyKeyboard, caption, null));
    }

    @Override
    public Message sendAudio(File file, String caption, String title) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), null, null, null, caption, title));
    }

    @Override
    public Message sendAudio(File file, String caption, String title, Integer replyMessageId) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), null, replyMessageId, null, caption, title));
    }

    @Override
    public Message sendAudio(File file, String caption, String title, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), null, null, replyKeyboard, caption, title));
    }

    @Override
    public Message sendAudio(File file, Long chatId, String caption, String title) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), chatId, null, null, caption, title));
    }

    @Override
    public Message sendAudio(File file, Long chatId, String caption, String title, Integer replyMessageId) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), chatId, replyMessageId, null, caption, title));
    }

    @Override
    public Message sendAudio(File file, Long chatId, String caption, String title, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(file), chatId, null, replyKeyboard, caption, title));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), null, null, null, null, null));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, Long chatId) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), chatId, null, null, null, null));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, String caption) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), null, null, null, caption, null));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, Integer replyMessageId) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), null, replyMessageId, null, null, null));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), null, null, replyKeyboard, null, null));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, Long chatId, String caption) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), chatId, null, null, caption, null));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, Long chatId, Integer replyMessageId) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), chatId, replyMessageId, null, null, null));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, Long chatId, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), chatId, null, replyKeyboard, null, null));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, String caption, Integer replyMessageId) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), null, replyMessageId, null, caption, null));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, String caption, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), null, null, replyKeyboard, caption, null));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, String caption, String title) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), null, null, null, caption, title));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, String caption, String title, Integer replyMessageId) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), null, replyMessageId, null, caption, title));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, String caption, String title, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), null, null, replyKeyboard, caption, title));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, Long chatId, String caption, String title) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), chatId, null, null, caption, title));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, Long chatId, String caption, String title, Integer replyMessageId) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), chatId, replyMessageId, null, caption, title));
    }

    @Override
    public Message sendAudio(InputStream inputStream, String filename, Long chatId, String caption, String title, ReplyKeyboard replyKeyboard) {
        return tryExecuteSendAudio(sendAudioBuilder(new InputFile(inputStream, filename), chatId, null, replyKeyboard, caption, title));
    }

    @Override
    public Message sendAudio(SendAudio sendAudio) {
        sendAudio.setChatId(getChatId(Long.valueOf(sendAudio.getChatId())));
        return tryExecuteSendAudio(sendAudio);
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

    private AnswerCallbackQuery answerCallbackQueryBuilder(String text, String callbackQueryId, String url, Boolean showAlert) {
        return AnswerCallbackQuery.builder()
                .text(text)
                .url(url)
                .showAlert(showAlert)
                .callbackQueryId(getCallbackQueryId(callbackQueryId))
                .build();
    }

    private SendDice sendDiceBuilder(String emoji, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard) {
        return SendDice.builder()
                .emoji(emoji)
                .chatId(getChatId(chatId))
                .replyToMessageId(replyMessageId)
                .replyMarkup(replyKeyboard)
                .build();
    }

    private SendAudio sendAudioBuilder(InputFile inputFile, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard, String caption, String title) {
        return SendAudio.builder()
                .audio(inputFile)
                .chatId(getChatId(chatId))
                .replyToMessageId(replyMessageId)
                .replyMarkup(replyKeyboard)
                .caption(caption)
                .title(title)
                .build();
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
        return Objects.isNull(chatId) ? UpdateUtils.getChatIdFromUpdate(getUpdate()) : chatId;
    }

    private String getCallbackQueryId(String callbackId) {
        return Objects.isNull(callbackId) ? getUpdate().getCallbackQuery().getId() : callbackId;
    }

    private Message tryExecuteBotMethod(BotApiMethodMessage message) {
        try {
            return getInstance().execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private Boolean tryExecuteAnswerCallbackQuery(AnswerCallbackQuery answerCallbackQuery) {
        try {
            return getInstance().execute(answerCallbackQuery);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private Message tryExecuteSendAudio(SendAudio sendAudio) {
        try {
            return getInstance().execute(sendAudio);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private Message tryExecuteSendDocument(SendDocument sendDocument) {
        try {
            return getInstance().execute(sendDocument);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private Update getUpdate() {
        return FluentContextHolder.getCurrentUpdate().getUpdate();
    }

    private FluentBot getInstance() {
        if (Objects.isNull(this.instance)) {
            this.instance = singletonBot.getInstance();
        }
        return this.instance;
    }
}
