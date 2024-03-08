package com.example.fluenttest.custom.annotation;

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

    public HandleStickerMatcher(ExpressionMatcherService expressionMatcherService) {
        super(expressionMatcherService);
    }

    @Override
    public boolean matcher(HandleSticker annotation, Sticker value) {
        return expressionMatcherService.match(annotation.match(), annotation.value(), value.getType());
    }
}
