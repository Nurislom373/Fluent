package org.khasanof.mediator;

import org.khasanof.enums.DefaultMethodType;
import org.khasanof.service.invoker.MethodTypeDefinition;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.mediator
 * @since 1/27/2024 8:58 PM
 */
public class DefaultMethodTypeDefinitionMediator implements MethodTypeDefinitionMediator {

    private final List<MethodTypeDefinition> methodTypeDefinitions = new ArrayList<>();

    /**
     *
     * @param method
     * @return
     */
    @Override
    public DefaultMethodType definition(Method method) {
        return internalDefinition(method);
    }

    private DefaultMethodType internalDefinition(Method method) {
        return methodTypeDefinitions.stream()
                .sorted(Comparator.comparing(MethodTypeDefinition::getOrder))
                .filter(def -> def.isMatch(method))
                .findFirst()
                .map(MethodTypeDefinition::methodType)
                .orElseThrow(() -> new RuntimeException("Method Type not found!"));
    }

    /**
     *
     * @param methodTypeDefinitions
     */
    @Override
    public void addMethodTypeDefinitions(List<MethodTypeDefinition> methodTypeDefinitions) {
        if (Objects.nonNull(methodTypeDefinitions) && !methodTypeDefinitions.isEmpty()) {
           this.methodTypeDefinitions.addAll(methodTypeDefinitions);
        }
    }

    /**
     *
     * @param methodTypeDefinition
     */
    @Override
    public void addMethodTypeDefinition(MethodTypeDefinition methodTypeDefinition) {
        if (Objects.nonNull(methodTypeDefinition)) {
            this.methodTypeDefinitions.add(methodTypeDefinition);
        }
    }
}
