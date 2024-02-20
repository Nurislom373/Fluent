package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleDocument;
import org.khasanof.config.ApplicationConstants;
import org.khasanof.enums.scopes.DocumentScope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 28.06.2023 10:27
 */
@Component
public class SimpleDocumentMatcher extends GenericMatcher<HandleDocument, Document> {

    private final Map<DocumentScope, Function<Document, Object>> biFunctionMap = new HashMap<>();

    {
        setFunctionMap();
    }

    public SimpleDocumentMatcher() {
        super(ApplicationConstants.MATCHER_MAP);
    }

    @Override
    public boolean matcher(HandleDocument annotation, Document value) {
        Object apply = biFunctionMap.get(annotation.scope()).apply(value);
        return matchFunctions.get(Map.entry(annotation.match(), getMatchType(apply, annotation.match())))
                .apply(annotation.value(), apply);
    }

    @Override
    public Class<HandleDocument> getType() {
        return HandleDocument.class;
    }

    private void setFunctionMap() {
        biFunctionMap.put(DocumentScope.FILE_NAME, Document::getFileName);
        biFunctionMap.put(DocumentScope.MIME_TYPE, Document::getMimeType);
        biFunctionMap.put(DocumentScope.FILE_SIZE, Document::getFileSize);
    }


}
