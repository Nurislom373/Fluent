package org.khasanof.mediator;

import org.khasanof.feature.method.MethodType;
import org.khasanof.service.invoker.MethodTypeDefinition;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.mediator
 * @since 1/27/2024 8:57 PM
 */
public interface MethodTypeDefinitionMediator {

    MethodType definition(Method method);

    void addMethodTypeDefinitions(List<MethodTypeDefinition> methodTypeDefinitions);

    void addMethodTypeDefinition(MethodTypeDefinition methodTypeDefinition);

}
