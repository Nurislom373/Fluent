package org.khasanof.service.template.operations;

import org.telegram.telegrambots.meta.api.methods.send.SendContact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

/**
 * @author Abdulloh
 * @see org.khasanof.service.template.operations
 * @since 2/15/2024 10:40 PM
 */

public interface SendContactOperations {
    Message sendContact(String firstname, String phoneNumber);

    Message sendContact(String firstname, String phoneNumber, Long chatId);

    Message sendContact(String firstname, String phoneNumber, String lastname);

    Message sendContact(String firstname, String phoneNumber, Integer replyToMessageId);

    Message sendContact(String firstname, String phoneNumber, Boolean disableNotification);

    Message sendContact(String firstname, String phoneNumber, ReplyKeyboard replyKeyboard);

    Message sendContact(String firstname, String phoneNumber, Long chatId, String lastname);

    Message sendContact(String firstname, String phoneNumber, Long chatId, Integer replyToMessageId);

    Message sendContact(String firstname, String phoneNumber, Long chatId, Boolean disableNotification);

    Message sendContact(String firstname, String phoneNumber, Long chatId, ReplyKeyboard replyKeyboard);

    Message sendContact(String firstname, String phoneNumber, String lastname, Integer replyToMessageId);

    Message sendContact(String firstname, String phoneNumber, String lastname, Boolean disableNotification);

    Message sendContact(String firstname, String phoneNumber, String lastname, ReplyKeyboard replyKeyboard);

    Message sendContact(String firstname, String phoneNumber, Integer replyToMessageId, Boolean disableNotification);

    Message sendContact(String firstname, String phoneNumber, Integer replyToMessageId, ReplyKeyboard replyKeyboard);

    Message sendContact(String firstname, String phoneNumber, Boolean disableNotification, ReplyKeyboard replyKeyboard);


    Message sendContact(String firstname, String phoneNumber, Long chatId, String lastname, Integer replyToMessageId, ReplyKeyboard replyKeyboard);

    Message sendContact(String firstname, String phoneNumber, String lastname, Integer replyToMessageId, Boolean disableNotification);

    Message sendContact(String firstname, String phoneNumber, String lastname, Integer replyToMessageId, ReplyKeyboard replyKeyboard);

    Message sendContact(String firstname, String phoneNumber, Long chatId, String lastname, Integer replyToMessageId, Boolean disableNotification);

    Message sendContact(String firstname, String phoneNumber, Long chatId, String lastname, ReplyKeyboard replyKeyboard, Boolean disableNotification);

    Message sendContact(String firstname, String phoneNumber, String lastname, Integer replyToMessageId, Boolean disableNotification, ReplyKeyboard replyKeyboard);

    Message sendContact(String firstname, String phoneNumber, Long chatId, Integer replyToMessageId, Boolean disableNotification, ReplyKeyboard replyKeyboard);

    Message sendContact(SendContact contact);
}
