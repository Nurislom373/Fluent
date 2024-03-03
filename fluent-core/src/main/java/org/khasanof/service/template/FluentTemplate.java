package org.khasanof.service.template;

import org.khasanof.service.template.operations.ForwardMessageOperations;
import org.khasanof.service.template.operations.SendMessageOperations;
import org.khasanof.service.template.operations.invoice.InvoiceOperations;
import org.khasanof.service.template.operations.pin.PinMessageOperations;
import org.khasanof.service.template.operations.query.AnswerCallbackQueryOperations;
import org.khasanof.service.template.operations.query.AnswerQueriesOperations;

import javax.ws.rs.sse.InboundSseEvent;

/**
 * @author Nurislom
 * @see org.khasanof.service.template
 * @since 1/23/2024 9:23 PM
 */
public interface FluentTemplate extends SendMessageOperations, AnswerQueriesOperations, ForwardMessageOperations, PinMessageOperations/*, InvoiceOperations*/ {
}
