package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleDocument;
import org.khasanof.enums.scopes.DocumentScope;
import org.khasanof.models.matcher.MatcherParameters;
import org.khasanof.models.matcher.MatcherProperty;
import org.khasanof.models.matcher.function.PropertyFunction;
import org.khasanof.service.expression.ExpressionMatcherService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 28.06.2023 10:27
 */
@Component
public class SimpleDocumentMatcher extends GenericMatcher<HandleDocument, Message> {

    private final Set<MatcherProperty> matcherProperties = new HashSet<>();

    public SimpleDocumentMatcher(ExpressionMatcherService expressionMatcherService) {
        super(expressionMatcherService);
        fillMatcherProperties();
    }

    @Override
    public boolean matcher(HandleDocument annotation, Message value) {
        return expressionMatcherService.match(createParameter(annotation, value));
    }

    private MatcherParameters createParameter(HandleDocument annotation, Message value) {
        return new MatcherParameters(value, annotation.property(), annotation.match(), annotation.value(), matcherProperties);
    }

    private void fillMatcherProperties() {
        matcherProperties.add(new MatcherProperty(DocumentScope.CAPTION, (PropertyFunction<Message>) Message::getCaption));
        matcherProperties.add(new MatcherProperty(DocumentScope.FILE_NAME, (PropertyFunction<Message>) msg -> msg.getDocument().getFileName()));
        matcherProperties.add(new MatcherProperty(DocumentScope.MIME_TYPE, (PropertyFunction<Message>) msg -> msg.getDocument().getMimeType()));
        matcherProperties.add(new MatcherProperty(DocumentScope.FILE_SIZE, (PropertyFunction<Message>) msg -> msg.getDocument().getFileSize()));
    }
}
