package com.example.fluenttest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.khasanof.annotation.UpdateController;
import org.khasanof.annotation.methods.HandleAny;
import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.enums.HandleType;
import org.khasanof.enums.MatchScope;
import org.khasanof.enums.Proceed;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;

/**
 * @author Nurislom
 * @see com.example
 * @since 8/9/2023 9:44 PM
 */
@UpdateController
public class HandleAnyTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @HandleAny(type = HandleType.STICKER, proceed = Proceed.NOT_PROCEED)
    private void handleAnyStickers(Update update, AbsSender sender) throws TelegramApiException, JsonProcessingException {
        String value = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(update.getMessage().getSticker());
        String text = "I'm handle this sticker : \n" + value;
        SendMessage message = new SendMessage(update.getMessage().getChatId().toString(), text);
        sender.execute(message);
    }

    @HandleAny(type = HandleType.PHOTO, proceed = Proceed.PROCEED)
    private void handleAnyPhoto(Update update, AbsSender sender) throws TelegramApiException, JsonProcessingException {
        System.out.println("update.getMessage().getText() = " + update.getMessage().getCaption());
        String value = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(update.getMessage().getPhoto());
        String text = "handle any this photo : \n" + value;
        SendMessage message = new SendMessage(update.getMessage().getChatId().toString(), text);
        sender.execute(message);
    }

    @HandleAny(type = HandleType.DOCUMENT, proceed = Proceed.PROCEED)
    private void handleAnyDocument(Update update, AbsSender sender) throws TelegramApiException, JsonProcessingException {
        String value = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(update.getMessage().getDocument());
        String text = "I'm handle this document : \n" + value;
        SendMessage message = new SendMessage(update.getMessage().getChatId().toString(), text);
        sender.execute(message);
    }

    @HandleAny(type = HandleType.AUDIO, proceed = Proceed.PROCEED)
    private void handleAnyCallbacks(Update update, AbsSender sender) throws TelegramApiException, JsonProcessingException {
        String value = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(update.getMessage().getAudio());
        String text = "I'm handle this audio : \n" + value;
        SendMessage message = new SendMessage(update.getMessage().getChatId().toString(), text);
        sender.execute(message);
    }

    @HandleAny(type = HandleType.VIDEO_NOTE, proceed = Proceed.PROCEED)
    private void handleAnyVideoNote(Update update, AbsSender sender) throws TelegramApiException, JsonProcessingException {
        String value = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(update.getMessage().getVideoNote());
        String text = "I'm handle this video note : \n" + value;
        SendMessage message = new SendMessage(update.getMessage().getChatId().toString(), text);
        sender.execute(message);
    }

    @HandleAny(type = HandleType.VIDEO, proceed = Proceed.PROCEED)
    private void handleAnyPhotos(Update update, AbsSender sender) throws TelegramApiException, JsonProcessingException {
        String value = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(update.getMessage().getVideo());
        String text = "I'm handle this video : \n" + value;
        SendPhoto sendPhoto = new SendPhoto(update.getMessage().getChatId().toString(),
                new InputFile(new File("...")));
        sender.execute(sendPhoto);
    }

}
