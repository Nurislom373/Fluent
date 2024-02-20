package com.example.fluenttest.custom;

import org.khasanof.annotation.process.ProcessUpdate;
import org.khasanof.enums.MatchScope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Nurislom
 * @see com.example.fluenttest.custom
 * @since 2/20/2024 11:42 PM
 */
@ProcessUpdate
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleSticker {

    String value();

    MatchScope match() default MatchScope.EQUALS;

    HandleStickerProperty property() default HandleStickerProperty.TYPE;
}
