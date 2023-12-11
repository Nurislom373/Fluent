package org.khasanof.model.invoker;

import lombok.*;
import org.khasanof.enums.InvokerType;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.model.invoker
 * @since 8/8/2023 9:31 PM
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SimpleInvokerMethod implements SimpleInvoker {

    private Method method;
    private Object reference;

    @Override
    public InvokerType getType() {
        return InvokerType.METHOD;
    }
}
