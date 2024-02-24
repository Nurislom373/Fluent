package org.khasanof.annotation.methods;

import org.khasanof.annotation.process.ProcessFile;
import org.khasanof.annotation.process.ProcessUpdate;
import org.khasanof.enums.MatchType;
import org.khasanof.enums.scopes.DocumentScope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: Nurislom
 * <br/>
 * Date: 21.06.2023
 * <br/>
 * Time: 23:41
 * <br/>
 *
 * Package: org.khasanof.main.annotation
 */
@ProcessFile
@ProcessUpdate
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleDocument {

    String value();

    MatchType match() default MatchType.EQUALS;

    DocumentScope property() default DocumentScope.FILE_NAME;

}
