package org.khasanof.config;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.annotation.UpdateController;
import org.khasanof.annotation.methods.HandleDocument;
import org.khasanof.enums.MatchType;
import org.khasanof.enums.scopes.DocumentScope;
import org.khasanof.service.template.FluentTemplate;

/**
 * @author Nurislom
 * @see org.khasanof.config
 * @since 2/24/2024 11:07 PM
 */
@Slf4j
@UpdateController
public class TestDocumentHandler {

    private final FluentTemplate fluentTemplate;

    public TestDocumentHandler(FluentTemplate fluentTemplate) {
        this.fluentTemplate = fluentTemplate;
    }

    @HandleDocument(
            value = "([a-zA-Z0-9\\s_\\\\.\\-\\(\\):])+(.jpeg|.png|.pdf|.patch)$",
            match = MatchType.REGEX,
            property = DocumentScope.FILE_NAME
    )
    private void handleDocumentOne() {
        String text = "I Handle 1 File";
        fluentTemplate.sendText(text);
    }

    @HandleDocument(value = "food", match = MatchType.START_WITH, property = DocumentScope.FILE_NAME)
    private void handleDocumentStartWithFood() {
        String text = "I Handle file name start with food";
        fluentTemplate.sendText(text);
    }
}
