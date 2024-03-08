package org.khasanof.service.exception;

import lombok.*;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof.service.exception
 * @since 1/27/2024 6:29 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResolver {

    private Update update;
    private Throwable throwable;

}
