package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandlePhoto;
import org.khasanof.annotation.methods.HandlePhotos;
import org.khasanof.config.ApplicationConstants;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Arrays;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 06.07.2023 22:57
 */
@Component
public class SimplePhotosMatcher extends MultiGenericMatcher<HandlePhotos, HandlePhoto, Message> {

    public SimplePhotosMatcher(GenericMatcher<HandlePhoto, Message> matcher) {
        super(matcher, ApplicationConstants.MATCHER_MAP);
    }

    @Override
    public boolean matcher(HandlePhotos annotation, Message value) {
        return Arrays.stream(annotation.values())
                .anyMatch(handlePhoto -> matcher.matcher(handlePhoto, value));
    }

    @Override
    public Class<HandlePhotos> getType() {
        return HandlePhotos.class;
    }
}
