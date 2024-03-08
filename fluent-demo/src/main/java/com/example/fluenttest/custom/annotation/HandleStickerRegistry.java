package com.example.fluenttest.custom.annotation;

import org.khasanof.enums.DefaultMethodType;
import org.khasanof.executors.appropriate.AppropriateUpdateMethod;
import org.khasanof.executors.appropriate.AppropriateUpdateType;
import org.khasanof.executors.appropriate.type.MessageUpdateTypeMatcher;
import org.khasanof.executors.matcher.GenericMatcher;
import org.khasanof.feature.annotation.AnnotationHandler;
import org.khasanof.feature.annotation.HandlerAnnotationRegistry;
import org.khasanof.feature.method.MethodType;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see com.example.fluenttest.custom
 * @since 2/20/2024 11:48 PM
 */
@Component
public class HandleStickerRegistry implements HandlerAnnotationRegistry {

    @Override
    public AnnotationHandler getAnnotationHandler() {
        return () -> HandleSticker.class;
    }

    @Override
    public MethodType getMethodType() {
        return DefaultMethodType.DEFAULT;
    }

    @Override
    public Class<? extends GenericMatcher> getMatcher() {
        return HandleStickerMatcher.class;
    }

    @Override
    public Class<? extends AppropriateUpdateType> getAppropriateUpdateType() {
        return MessageUpdateTypeMatcher.class;
    }

    @Override
    public Class<? extends AppropriateUpdateMethod> getAppropriateUpdateMethod() {
        return StickerMessageAppropriateMethod.class;
    }
}
