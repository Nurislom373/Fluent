package org.khasanof.annotation.methods;

import org.khasanof.annotation.process.ProcessContainer;
import org.khasanof.annotation.process.ProcessUpdate;
import org.khasanof.enums.RepeatableMatchType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: Nurislom
 * <br/>
 * Date: 22.06.2023
 * <br/>
 * Time: 20:34
 * <br/>
 * Package: org.khasanof.main.annotation
 */
@ProcessUpdate
@ProcessContainer
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleMessages {

    HandleMessage[] value();

    RepeatableMatchType match() default RepeatableMatchType.ANY_MATCH;
}
