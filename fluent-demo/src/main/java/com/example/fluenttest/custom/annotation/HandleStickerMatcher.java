package com.example.fluenttest.custom.annotation;

import org.khasanof.config.ApplicationConstants;
import org.khasanof.executors.matcher.GenericMatcher;
import org.khasanof.service.expression.ExpressionMatcherService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.stickers.Sticker;

/**
 * @author Nurislom
 * @see com.example.fluenttest.custom
 * @since 2/20/2024 11:50 PM
 */
@Component
public class HandleStickerMatcher extends GenericMatcher<HandleSticker, Sticker> {

    private final ExpressionMatcherService expressionMatcherService;

    public HandleStickerMatcher(ExpressionMatcherService expressionMatcherService) {
        super(ApplicationConstants.MATCHER_MAP);
        this.expressionMatcherService = expressionMatcherService;
    }

    @Override
    public boolean matcher(HandleSticker annotation, Sticker value) {
//        String type = value.getType();
//        return matchFunctions.get(Map.entry(annotation.match(), getMatchType(type, annotation.match())))
//                .apply(annotation.value(), type);
        return expressionMatcherService.match(annotation.match(), annotation.value(), value.getType());
    }
}
