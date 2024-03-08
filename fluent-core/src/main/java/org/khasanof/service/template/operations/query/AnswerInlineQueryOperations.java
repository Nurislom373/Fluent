package org.khasanof.service.template.operations.query;

import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.service.template.operations.query
 * @since 2/15/2024 8:50 AM
 */
public interface AnswerInlineQueryOperations {

    Boolean sendAnswerInlineQuery(List<InlineQueryResult> results);

    Boolean sendAnswerInlineQuery(List<InlineQueryResult> results, String inlineQueryId);

    Boolean sendAnswerInlineQuery(List<InlineQueryResult> results, Integer cacheTime);

    Boolean sendAnswerInlineQuery(List<InlineQueryResult> results, Boolean isPersonal);

    Boolean sendAnswerInlineQuery(List<InlineQueryResult> results, String inlineQueryId, Integer cacheTime);

    Boolean sendAnswerInlineQuery(List<InlineQueryResult> results, String inlineQueryId, Boolean isPersonal);

    Boolean sendAnswerInlineQuery(List<InlineQueryResult> results, String inlineQueryId, Integer cacheTime, Boolean isPersonal);

    Boolean sendAnswerInlineQuery(AnswerInlineQuery answerInlineQuery);
}
