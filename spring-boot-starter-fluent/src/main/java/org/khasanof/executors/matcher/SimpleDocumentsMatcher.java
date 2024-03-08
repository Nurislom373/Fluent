package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleDocument;
import org.khasanof.annotation.methods.HandleDocuments;
import org.khasanof.models.matcher.RepeatableMatcherParameters;
import org.khasanof.service.expression.MultiExpressionMatcherService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 06.07.2023 22:35
 */
@Component
public class SimpleDocumentsMatcher extends MultiGenericMatcher<HandleDocuments, Message> {

    protected SimpleDocumentsMatcher(GenericMatcher<HandleDocument, Message> matcher, MultiExpressionMatcherService expressionMatcherService) {
        super(matcher, expressionMatcherService);
    }

    @Override
    public boolean matcher(HandleDocuments annotation, Message value) {
        return expressionMatcherService.match(new RepeatableMatcherParameters(annotation.match(), annotation.value(), matcher, value));
    }
}
