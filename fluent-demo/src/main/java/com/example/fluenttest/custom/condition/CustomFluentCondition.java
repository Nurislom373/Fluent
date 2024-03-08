package com.example.fluenttest.custom.condition;

import org.khasanof.constants.AttributesConstants;
import org.khasanof.custom.attributes.Attributes;
import org.khasanof.feature.condition.FluentCondition;
import org.khasanof.models.meta.AnnotatedTypeMetadata;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see com.example.fluenttest.custom.condition
 * @since 2/29/2024 12:28 AM
 */
public class CustomFluentCondition implements FluentCondition {

    @Override
    public boolean matches(Attributes attributes, AnnotatedTypeMetadata metadata) {
        Update update = (Update) attributes.getAttribute(AttributesConstants.UPDATE_ATTRIBUTE);
        return update.hasMessage();
    }
}
