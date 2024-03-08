package org.khasanof.models.invoker;

import lombok.*;
import org.khasanof.enums.InvokerType;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
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
    private Boolean hasMethodParams;
    private Map<InvokerParam, Object> params;

    public SimpleInvokerMethod(Method method, Object reference) {
        this.method = method;
        this.reference = reference;
    }

    @Override
    public InvokerType getType() {
        return InvokerType.METHOD;
    }

    @Override
    public Boolean hasMethodParams() {
        return Objects.equals(hasMethodParams, Boolean.TRUE);
    }
}
