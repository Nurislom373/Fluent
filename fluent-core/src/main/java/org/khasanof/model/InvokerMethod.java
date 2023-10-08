package org.khasanof.model;

import lombok.*;
import org.khasanof.enums.InvokerType;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.model
 * @since 8/8/2023 9:31 PM
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InvokerMethod implements InvokerResult {

    private Method method;
    private Object reference;

    @Override
    public InvokerType getType() {
        return InvokerType.METHOD;
    }
}
