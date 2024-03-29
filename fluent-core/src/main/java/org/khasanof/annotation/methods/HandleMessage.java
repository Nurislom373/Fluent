package org.khasanof.annotation.methods;

import org.khasanof.annotation.process.ProcessUpdate;
import org.khasanof.enums.MatchType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: Nurislom
 * <br/>
 * Date: 18.06.2023
 * <br/>
 * Time: 10:25
 * <br/>
 * Package: org.khasanof.main.annotation
 */
@ProcessUpdate
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleMessage {

    String value() default "/";

    MatchType match() default MatchType.EQUALS;
}
