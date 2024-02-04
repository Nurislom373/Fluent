package com.example.fluenttest;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.annotation.ExceptionController;
import org.khasanof.annotation.exception.HandleException;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see com.example.fluenttest
 * @since 2/3/2024 11:40 PM
 */
@Slf4j
@ExceptionController
public class SimpleExceptionHandler {

    @HandleException({RuntimeException.class})
    public void handleRuntimeException(RuntimeException exception) {
        log.info("Exception handle : {}", exception.getMessage());
    }

}
