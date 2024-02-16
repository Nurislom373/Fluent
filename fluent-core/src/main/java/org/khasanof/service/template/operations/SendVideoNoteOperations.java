package org.khasanof.service.template.operations;

import org.telegram.telegrambots.meta.api.methods.send.SendVideoNote;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.io.File;
import java.io.InputStream;

/**
 * @author Rakh1sta
 * @see org.khasanof.service.template.operations
 * @since 2/15/2024 9:57 PM
 */
public interface SendVideoNoteOperations {

    Message sendVideoNote(File file);

    Message sendVideoNote(File file, Long chatId);

    Message sendVideoNote(File file, Integer replyToMessageId);

    Message sendVideoNote(File file, ReplyKeyboard replyMarkup);

    Message sendVideoNote(File file, Long chatId, Integer replyMessageId);

    Message sendVideoNote(File file, Long chatId, ReplyKeyboard replyMarkup);

    Message sendVideoNote(File file, Integer replyToMessageId, ReplyKeyboard replyMarkup);

    Message sendVideoNote(File file, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard);


    // Input Stream
    Message sendVideoNote(InputStream stream, String filename);

    Message sendVideoNote(InputStream stream, String filename, Long chatId);

    Message sendVideoNote(InputStream stream, String filename, Integer replyToMessageId);

    Message sendVideoNote(InputStream stream, String filename, ReplyKeyboard replyMarkup);

    Message sendVideoNote(InputStream stream, String filename, Long chatId, Integer replyMessageId);

    Message sendVideoNote(InputStream stream, String filename, Long chatId, ReplyKeyboard replyMarkup);

    Message sendVideoNote(InputStream stream, String filename, Integer replyToMessageId, ReplyKeyboard replyMarkup);

    Message sendVideoNote(InputStream stream, String filename, Long chatId, Integer replyMessageId, ReplyKeyboard replyKeyboard);

    Message sendVideoNote(SendVideoNote sendVideoNote);
}
