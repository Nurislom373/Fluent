package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandlePhoto;
import org.khasanof.enums.scopes.PhotoScope;
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
 * @since 06.07.2023 22:37
 */
@Component
public class SimplePhotoMatcher extends GenericMatcher<HandlePhoto, Message> {

    private final Set<MatcherProperty> matcherProperties = new HashSet<>();

    public SimplePhotoMatcher(ExpressionMatcherService expressionMatcherService) {
        super(expressionMatcherService);
        fillMatcherProperties();
    }

    @Override
    public boolean matcher(HandlePhoto annotation, Message value) {
        return expressionMatcherService.match(createParameters(annotation, value));
    }

    private MatcherParameters createParameters(HandlePhoto annotation, Message value) {
        return new MatcherParameters(value, annotation.property(), annotation.match(), annotation.value(), matcherProperties);
    }

    void fillMatcherProperties() {
        matcherProperties.add(new MatcherProperty(PhotoScope.CAPTION, (PropertyFunction<Message>) Message::getCaption));
        matcherProperties.add(new MatcherProperty(PhotoScope.HEIGHT, getPhotoHeightFunction()));
        matcherProperties.add(new MatcherProperty(PhotoScope.WIDTH, getPhotoWidthFunction()));
        matcherProperties.add(new MatcherProperty(PhotoScope.FILE_SIZE, getPhotoFilesizeFunction()));
    }

    private PropertyFunction<Message> getPhotoHeightFunction() {
        return message -> message.getPhoto().get(message.getPhoto().size() - 1).getHeight();
    }

    private PropertyFunction<Message> getPhotoWidthFunction() {
        return message -> message.getPhoto().get(message.getPhoto().size() - 1).getWidth();
    }

    private PropertyFunction<Message> getPhotoFilesizeFunction() {
        return message -> message.getPhoto().get(message.getPhoto().size() - 1).getFileSize();
    }
}
