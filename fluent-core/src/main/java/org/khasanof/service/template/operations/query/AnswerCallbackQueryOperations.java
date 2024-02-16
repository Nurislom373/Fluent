package org.khasanof.service.template.operations.query;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;

/**
 * @author Nurislom
 * @see org.khasanof.service.template.operations.query
 * @since 2/3/2024 11:20 PM
 */
public interface AnswerCallbackQueryOperations {

    Boolean sendAnswerCallbackQuery(String text);

    Boolean sendAnswerCallbackQuery(String text, String callbackQueryId);

    Boolean sendAnswerCallbackQuery(String text, Boolean showAlert);

    Boolean sendAnswerCallbackQuery(String text, String callbackQueryId, String url);

    Boolean sendAnswerCallbackQuery(String text, String callbackQueryId, Boolean showAlert);

    Boolean sendAnswerCallbackQuery(String text, String callbackQueryId, String url, Boolean showAlert);

    Boolean sendAnswerCallbackQuery(AnswerCallbackQuery answerCallbackQuery);
}
