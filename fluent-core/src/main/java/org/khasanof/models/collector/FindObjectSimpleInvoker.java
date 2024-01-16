package org.khasanof.models.collector;

import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.models.collector
 * @since 1/15/2024 10:43 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FindObjectSimpleInvoker implements FindSimpleInvoker<Enum> {

    private Object value;
    private Enum key;

}
