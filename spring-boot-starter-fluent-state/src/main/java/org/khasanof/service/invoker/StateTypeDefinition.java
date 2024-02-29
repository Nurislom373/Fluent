package org.khasanof.service.invoker;

import org.khasanof.enums.DefaultMethodType;
import org.khasanof.feature.method.MethodType;
import org.khasanof.state.StateAction;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Nurislom
 * @see org.khasanof.service.invoker
 * @since 2/29/2024 11:16 PM
 */
@Component
public class StateTypeDefinition implements MethodTypeDefinition {

    @Override
    public boolean isMatch(Method method) {
        return Arrays.asList(method.getDeclaringClass().getInterfaces()).contains(StateAction.class);
    }

    @Override
    public MethodType methodType() {
        return DefaultMethodType.STATE;
    }
}
