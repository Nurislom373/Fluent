package org.khasanof.service.handler.decorator.impls;

import org.khasanof.enums.DefaultMethodType;
import org.khasanof.feature.annotation.HandlerAnnotationRegistry;
import org.khasanof.feature.method.MethodType;
import org.khasanof.mediator.MethodTypeDefinitionMediator;
import org.khasanof.service.handler.decorator.BaseHandlerAnnotationDecorator;
import org.khasanof.service.invoker.MethodTypeDefinition;
import org.khasanof.utils.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.service.handler.decorator.impls
 * @since 2/20/2024 9:12 PM
 */
public class MethodTypeAnnotationHandlerDecorator extends BaseHandlerAnnotationDecorator {

    private final MethodTypeDefinitionMediator methodTypeDefinitionMediator;

    public MethodTypeAnnotationHandlerDecorator(MethodTypeDefinitionMediator methodTypeDefinitionMediator) {
        this.methodTypeDefinitionMediator = methodTypeDefinitionMediator;
    }

    @Override
    public void execute(HandlerAnnotationRegistry registry) {
        super.execute(registry);
        internalExecute(registry);
    }

    private void internalExecute(HandlerAnnotationRegistry registry) {
        if (!isExistMethodType(registry.getMethodType())) {
            methodTypeDefinitionMediator.addMethodTypeDefinition(definitionBuilder(registry));
        }
    }

    private boolean isExistMethodType(MethodType methodType) {
        return Arrays.stream(DefaultMethodType.values())
                .anyMatch(defaultMethodType -> Objects.equals(defaultMethodType.getName(), methodType.getName()));
    }

    private MethodTypeDefinition definitionBuilder(HandlerAnnotationRegistry registry) {
        return new MethodTypeDefinition() {
            @Override
            public boolean isMatch(Method method) {
                return AnnotationUtils.hasAnnotation(method, registry.getAnnotationHandler().getAnnotation(), false);
            }

            @Override
            public MethodType methodType() {
                return registry.getMethodType();
            }
        };
    }
}
