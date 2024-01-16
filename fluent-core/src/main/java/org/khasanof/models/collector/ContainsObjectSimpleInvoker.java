package org.khasanof.models.collector;

import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.models.collector
 * @since 1/15/2024 10:53 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ContainsObjectSimpleInvoker implements ContainsSimpleInvoker<Enum> {

    private Enum key;

}
