package org.khasanof.models.collector;

import lombok.*;
import org.khasanof.enums.HandleClasses;

/**
 * @author Nurislom
 * @see org.khasanof.models.collector
 * @since 1/15/2024 10:30 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FindHandlerMethod {

    private Object value;
    private HandleClasses key;

}
