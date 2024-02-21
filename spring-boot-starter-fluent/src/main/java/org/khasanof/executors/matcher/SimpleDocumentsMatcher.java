package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleDocument;
import org.khasanof.annotation.methods.HandleDocuments;
import org.khasanof.config.ApplicationConstants;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Document;

import java.util.Arrays;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 06.07.2023 22:35
 */
@Component
public class SimpleDocumentsMatcher extends MultiGenericMatcher<HandleDocuments, HandleDocument, Document> {

    protected SimpleDocumentsMatcher(GenericMatcher<HandleDocument, Document> matcher) {
        super(matcher, ApplicationConstants.MATCHER_MAP);
    }

    @Override
    public boolean matcher(HandleDocuments annotation, Document value) {
        return Arrays.stream(annotation.value())
                .anyMatch(handleDocument -> matcher.matcher(handleDocument, value));
    }

    @Override
    public Class<HandleDocuments> getType() {
        return HandleDocuments.class;
    }
}
