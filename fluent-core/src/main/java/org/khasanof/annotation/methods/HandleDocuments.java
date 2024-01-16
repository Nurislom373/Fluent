package org.khasanof.annotation.methods;

import org.khasanof.annotation.process.ProcessContainer;
import org.khasanof.annotation.process.ProcessFile;
import org.khasanof.annotation.process.ProcessUpdate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Nurislom
 * @see org.khasanof.annotation.methods
 * @since 04.07.2023 0:33
 */
@ProcessFile
@ProcessUpdate
@ProcessContainer
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleDocuments {

    HandleDocument[] values();

}
