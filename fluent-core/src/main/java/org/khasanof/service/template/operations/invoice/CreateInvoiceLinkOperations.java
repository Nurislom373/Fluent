package org.khasanof.service.template.operations.invoice;

import org.telegram.telegrambots.meta.api.methods.invoices.CreateInvoiceLink;
import org.telegram.telegrambots.meta.api.objects.payments.LabeledPrice;

import java.util.List;

/**
 * @author Rakh1sta
 * @see org.khasanof.service.template.operations.query
 * @since 4/03/2024 1:35 AM
 */
public interface CreateInvoiceLinkOperations {
    String createInvoiceLink(String title);
    String createInvoiceLink(String title,String description);;
    String createInvoiceLink(String title,String description, String payload);
    String createInvoiceLink(String title,String description, String payload,String currency);
    String createInvoiceLink(String title, List<LabeledPrice> prices);
    String createInvoiceLink(String title,String description, List<LabeledPrice> prices);
    String createInvoiceLink(String title, List<LabeledPrice> prices,String payload);
    String createInvoiceLink(String title,String payload,String currency, List<LabeledPrice> prices);



    String createInvoiceLink(CreateInvoiceLink createInvoiceLink);
}
