package org.khasanof.service.template.operations;

import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.methods.send.SendVoice;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.io.File;
import java.io.InputStream;

/**
 * @author Rakh1sta
 * @see org.khasanof.service.template.operations
 * @since 2/15/2024 9:57 PM
 */
public interface SendVoiceOperations {
    Message sendVoice(File file);
    Message sendVoice(File file, Long chatId);
    Message sendVoice(File file, String caption);
    Message sendVoice(File file, Integer replyToMessageId);
    Message sendVoice(File file, ReplyKeyboard replyMarkup);
    Message sendVoice(File file, Long chatId, String caption);
    Message sendVoice(File file, Long chatId, Integer replyMessageId);
    Message sendVoice(File file, Long chatId, ReplyKeyboard replyMarkup);
    Message sendVoice(File file, String caption, Integer replyMessageId);
    Message sendVoice(File file, String caption, ReplyKeyboard replyMarkup);
    Message sendVoice(File file, Integer replyToMessageId, ReplyKeyboard replyMarkup);
    Message sendVoice(File file, Long chatId,String caption, Integer replyMessageId);
    Message sendVoice(File file, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard);
    Message sendVoice(File file, Long chatId, String caption, ReplyKeyboard replyMarkup);
    Message sendVoice(File file, String caption, Integer replyMessageId,ReplyKeyboard replyMarkup);
    Message sendVoice(File file, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId);


    // Input StrVoice
    Message sendVoice(InputStream stream, String filename);
    Message sendVoice(InputStream stream, String filename, Long chatId);
    Message sendVoice(InputStream stream, String filename, String caption);
    Message sendVoice(InputStream stream, String filename, Integer replyToMessageId);
    Message sendVoice(InputStream stream, String filename, ReplyKeyboard replyMarkup);
    Message sendVoice(InputStream stream, String filename, Long chatId, String caption);
    Message sendVoice(InputStream stream, String filename, Long chatId, Integer replyMessageId);
    Message sendVoice(InputStream stream, String filename, Long chatId, ReplyKeyboard replyMarkup);
    Message sendVoice(InputStream stream, String filename, String caption, Integer replyMessageId);
    Message sendVoice(InputStream stream, String filename, String caption, ReplyKeyboard replyMarkup);
    Message sendVoice(InputStream stream, String filename, Integer replyToMessageId, ReplyKeyboard replyMarkup);
    Message sendVoice(InputStream stream, String filename, Long chatId,String caption, Integer replyMessageId);
    Message sendVoice(InputStream stream, String filename, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard);
    Message sendVoice(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup);
    Message sendVoice(InputStream stream, String filename, String caption, Integer replyMessageId,ReplyKeyboard replyMarkup);
    Message sendVoice(InputStream stream, String filename, Long chatId, String caption, ReplyKeyboard replyMarkup, Integer replyMessageId);

    Message sendVoice(SendVoice voice);
}
