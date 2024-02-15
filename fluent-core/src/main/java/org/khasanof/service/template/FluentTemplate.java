package org.khasanof.service.template;

import org.khasanof.service.template.operations.SendMessageOperations;
import org.khasanof.service.template.operations.query.AnswerCallbackQueryOperations;
import org.khasanof.service.template.operations.query.AnswerQueriesOperations;

/**
 * @author Nurislom
 * @see org.khasanof.service.template
 * @since 1/23/2024 9:23 PM
 */
public interface FluentTemplate extends SendMessageOperations, AnswerQueriesOperations {
}
