package org.khasanof.annotation.methods;

import org.khasanof.annotation.process.ProcessFile;
import org.khasanof.annotation.process.ProcessUpdate;
import org.khasanof.enums.MatchType;
import org.khasanof.enums.scopes.AudioScope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Nurislom
 * @see org.khasanof.annotation.methods
 * @since 09.07.2023 15:56
 */
@ProcessFile
@ProcessUpdate
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleAudio {

    String value();

    MatchType match() default MatchType.EQUALS;

    AudioScope property() default AudioScope.FILE_NAME;

}
