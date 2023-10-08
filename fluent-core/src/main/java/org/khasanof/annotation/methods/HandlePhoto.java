package org.khasanof.annotation.methods;

import org.khasanof.annotation.process.ProcessFile;
import org.khasanof.annotation.process.ProcessUpdate;
import org.khasanof.enums.MatchScope;
import org.khasanof.enums.scopes.PhotoScope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Nurislom
 * @see org.khasanof.annotation.methods
 * @since 06.07.2023 21:41
 */
@ProcessFile
@ProcessUpdate
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HandlePhoto {

    String value();

    MatchScope match() default MatchScope.EQUALS;

    PhotoScope scope() default PhotoScope.FILE_SIZE;

}
