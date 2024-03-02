package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandleVideo;
import org.khasanof.enums.scopes.VideoScope;
import org.khasanof.models.matcher.MatcherParameters;
import org.khasanof.models.matcher.MatcherProperty;
import org.khasanof.models.matcher.function.PropertyFunction;
import org.khasanof.service.expression.ExpressionMatcherService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Video;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 06.07.2023 23:34
 */
@Component
public class SimpleVideoMatcher extends GenericMatcher<HandleVideo, Message> {

    private final Set<MatcherProperty> matcherProperties = new HashSet<>();

    public SimpleVideoMatcher(ExpressionMatcherService expressionMatcherService) {
        super(expressionMatcherService);
        fillMatcherProperties();
    }

    @Override
    public boolean matcher(HandleVideo annotation, Message value) {
        return expressionMatcherService.match(createParameters(annotation, value));
    }

    private MatcherParameters createParameters(HandleVideo annotation, Message value) {
        return new MatcherParameters(value, annotation.property(), annotation.match(), annotation.value(), matcherProperties);
    }

    void fillMatcherProperties() {
        matcherProperties.add(new MatcherProperty(VideoScope.WIDTH, videoPropertyFunction(Video::getWidth)));
        matcherProperties.add(new MatcherProperty(VideoScope.HEIGHT, videoPropertyFunction(Video::getHeight)));
        matcherProperties.add(new MatcherProperty(VideoScope.DURATION, videoPropertyFunction(Video::getDuration)));
        matcherProperties.add(new MatcherProperty(VideoScope.FILE_NAME, videoPropertyFunction(Video::getFileName)));
        matcherProperties.add(new MatcherProperty(VideoScope.MIME_TYPE, videoPropertyFunction(Video::getMimeType)));
        matcherProperties.add(new MatcherProperty(VideoScope.FILE_SIZE, videoPropertyFunction(Video::getFileSize)));
        matcherProperties.add(new MatcherProperty(VideoScope.CAPTION, (PropertyFunction<Message>) Message::getCaption));
    }

    private PropertyFunction<Message> videoPropertyFunction(Function<Video, Object> function) {
        return message -> function.apply(message.getVideo());
    }
}
