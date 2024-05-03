package org.khasanof.service.template.operations.invoice;

import javassist.runtime.Desc;
import org.telegram.telegrambots.meta.api.methods.invoices.SendInvoice;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Rakh1sta
 * @see org.khasanof.service.template.operations
 * @since 2/16/2024 4:13 PM
 */
public interface SendInvoiceOperations {

    Message sendInvoice(String title);

    Message sendInvoice(String title, String description);

    Message sendInvoice(String title, Long chatId);

    Message sendInvoice(String title, String description, Long chatId);

    Message sendInvoice(String title, String description, String payload);

    Message sendInvoice(String title, String description, Long chatIt, String payload);

    Message sendInvoice(String title, Long chatIt, String payload);

    Message sendInvoice(String title, Long chatIt, String payload, String currency);
    Message sendInvoice(String title, String description, Long chatIt, String payload, String currency);


    Message sendInvoice(SendInvoice sendInvoice);
}


