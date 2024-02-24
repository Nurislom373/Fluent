package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleDocument;
import org.khasanof.config.ApplicationConstants;
import org.khasanof.enums.scopes.DocumentScope;
import org.khasanof.models.matcher.MatcherParameters;
import org.khasanof.models.matcher.MatcherProperty;
import org.khasanof.models.matcher.PropertyFunction;
import org.khasanof.service.expression.ExpressionMatcherService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Document;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 28.06.2023 10:27
 */
@Component
public class SimpleDocumentMatcher extends GenericMatcher<HandleDocument, Document> {

    private final Set<MatcherProperty> matcherProperties = new HashSet<>();
    private final ExpressionMatcherService expressionMatcherService;
    private final Map<DocumentScope, Function<Document, Object>> biFunctionMap = new HashMap<>();

    {
        setFunctionMap();
    }

    public SimpleDocumentMatcher(ExpressionMatcherService expressionMatcherService) {
        super(ApplicationConstants.MATCHER_MAP);
        this.expressionMatcherService = expressionMatcherService;
    }

    @Override
    public boolean matcher(HandleDocument annotation, Document value) {
//        Object apply = biFunctionMap.get(annotation.property()).apply(value);
//        return matchFunctions.get(Map.entry(annotation.match(), getMatchType(apply, annotation.match())))
//                .apply(annotation.value(), apply);
        return expressionMatcherService.match(new MatcherParameters(value, annotation.property(), annotation.match(), annotation.value(), matcherProperties));
    }

    @Override
    public Class<HandleDocument> getType() {
        return HandleDocument.class;
    }

    private void setFunctionMap() {
        biFunctionMap.put(DocumentScope.FILE_NAME, Document::getFileName);
        biFunctionMap.put(DocumentScope.MIME_TYPE, Document::getMimeType);
        biFunctionMap.put(DocumentScope.FILE_SIZE, Document::getFileSize);

        matcherProperties.add(new MatcherProperty(DocumentScope.FILE_NAME, (PropertyFunction<Document>) Document::getFileName));
        matcherProperties.add(new MatcherProperty(DocumentScope.MIME_TYPE, (PropertyFunction<Document>) Document::getMimeType));
        matcherProperties.add(new MatcherProperty(DocumentScope.FILE_SIZE, (PropertyFunction<Document>) Document::getFileSize));
    }
}
