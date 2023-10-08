package org.khasanof.model;

import lombok.*;
import org.khasanof.enums.InvokerType;

/**
 * @author Nurislom
 * @see org.khasanof.model
 * @since 8/19/2023 6:16 PM
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InvokerObject implements InvokerResult {

    private Object reference;
    private String executionMethodName;

    @Override
    public InvokerType getType() {
        return InvokerType.CLASS;
    }
}
