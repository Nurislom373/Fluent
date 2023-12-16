package org.khasanof.models.invoker;

import lombok.*;
import org.khasanof.enums.InvokerType;

/**
 * @author Nurislom
 * @see org.khasanof.models.invoker
 * @since 8/19/2023 6:16 PM
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SimpleInvokerObject implements SimpleInvoker {

    private Object reference;
    // TODO change method! idiot!
    private String executionMethodName;

    @Override
    public InvokerType getType() {
        return InvokerType.CLASS;
    }
}
