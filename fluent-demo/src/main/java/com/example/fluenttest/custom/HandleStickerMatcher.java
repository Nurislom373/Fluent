package com.example.fluenttest.custom;

import org.khasanof.config.ApplicationConstants;
import org.khasanof.executors.matcher.GenericMatcher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.stickers.Sticker;

import java.util.Map;

/**
 * @author Nurislom
 * @see com.example.fluenttest.custom
 * @since 2/20/2024 11:50 PM
 */
@Component
public class HandleStickerMatcher extends GenericMatcher<HandleSticker, Sticker> {

    public HandleStickerMatcher() {
        super(ApplicationConstants.MATCHER_MAP);
    }

    @Override
    public boolean matcher(HandleSticker annotation, Sticker value) {
        String type = value.getType();
        return matchFunctions.get(Map.entry(annotation.match(), getMatchType(type, annotation.match())))
                .apply(annotation.value(), type);
    }

    @Override
    public Class<HandleSticker> getType() {
        return HandleSticker.class;
    }
}
