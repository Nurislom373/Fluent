package org.khasanof.models.invoker;

import lombok.*;
import org.khasanof.ICondition;
import org.khasanof.enums.InvokerType;
import org.khasanof.models.condition.MethodCondition;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.locks.Condition;

/**
 * @author Nurislom
 * @see org.khasanof.models.invoker
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
    private Set<Condition> conditions;

    public SimpleInvokerMethod(Method method, Object reference) {
        this.method = method;
        this.reference = reference;
    }

    @Override
    public InvokerType getType() {
        return InvokerType.METHOD;
    }
}
