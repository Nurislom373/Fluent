package org.khasanof.annotation.methods;

import org.khasanof.annotation.process.ProcessFile;
import org.khasanof.annotation.process.ProcessUpdate;
import org.khasanof.enums.MatchType;
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

    MatchType match() default MatchType.EQUALS;

    PhotoScope property() default PhotoScope.CAPTION;
}
