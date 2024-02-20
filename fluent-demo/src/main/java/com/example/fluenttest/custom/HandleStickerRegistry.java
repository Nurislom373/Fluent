package com.example.fluenttest.custom;

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

    private final HandleStickerMatcher matcher;
    private final MessageUpdateTypeMatcher messageUpdateTypeMatcher;

    public HandleStickerRegistry(HandleStickerMatcher matcher,
                                 MessageUpdateTypeMatcher messageUpdateTypeMatcher) {

        this.matcher = matcher;
        this.messageUpdateTypeMatcher = messageUpdateTypeMatcher;
    }

    @Override
    public Class<?> getAnnotation() {
        return HandleSticker.class;
    }

    @Override
    public AnnotationHandler getAnnotationHandler() {
        return () -> HandleSticker.class;
    }

    @Override
    public MethodType getMethodType() {
        return DefaultMethodType.DEFAULT;
    }

    @Override
    public GenericMatcher getMatcher() {
        return this.matcher;
    }

    @Override
    public AppropriateUpdateType getAppropriateUpdateType() {
        return messageUpdateTypeMatcher;
    }

    @Override
    public AppropriateUpdateMethod getAppropriateUpdateMethod() {
        return new StickerMessageAppropriateMethod();
    }
}
