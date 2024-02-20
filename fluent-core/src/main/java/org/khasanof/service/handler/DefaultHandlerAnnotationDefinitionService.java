package org.khasanof.service.handler;

import lombok.*;
import org.khasanof.enums.DefaultMethodType;
import org.khasanof.feature.annotation.HandlerAnnotationRegistry;
import org.khasanof.registry.appropriate.AppropriateMethodRegistryContainer;
import org.khasanof.registry.appropriate.AppropriateTypeRegistryContainer;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.service.handler
 * @since 2/19/2024 10:54 PM
 */
public class DefaultHandlerAnnotationDefinitionService implements HandlerAnnotationDefinitionService {

    private final AppropriateTypeRegistryContainer typeRegistryContainer;
    private final AppropriateMethodRegistryContainer methodRegistryContainer;

    public DefaultHandlerAnnotationDefinitionService(AppropriateTypeRegistryContainer typeRegistryContainer,
                                                     AppropriateMethodRegistryContainer methodRegistryContainer) {

        this.typeRegistryContainer = typeRegistryContainer;
        this.methodRegistryContainer = methodRegistryContainer;
    }

    @Override
    public HandlerAnnotationDefinition definition(HandlerAnnotationRegistry registry) {
        var definition = new PrivateHandlerAnnotationDefinition();

        definition.setDefault(Objects.equals(registry.getMethodType(), DefaultMethodType.DEFAULT));
        definition.setRepeatable(Objects.equals(registry.getAnnotationHandler().isRepeatable(), Boolean.TRUE));
        definition.setNewAppropriateUpdateType(!isNewAppropriateUpdateType(registry));
        definition.setNewAppropriateUpdateMethod(!isNewAppropriateUpdateMethod(registry));
        return definition;
    }

    private boolean isNewAppropriateUpdateType(HandlerAnnotationRegistry registry) {
        return typeRegistryContainer.getAppropriateTypes().contains(registry.getAppropriateUpdateType());
    }

    private boolean isNewAppropriateUpdateMethod(HandlerAnnotationRegistry registry) {
        return methodRegistryContainer.getAppropriateMethods()
                .values().stream()
                .anyMatch(appropriates -> appropriates.contains(registry.getAppropriateUpdateMethod()));
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    private static class PrivateHandlerAnnotationDefinition implements HandlerAnnotationDefinition {

        private boolean isDefault;
        private boolean isRepeatable;
        private boolean isNewAppropriateUpdateType;
        private boolean isNewAppropriateUpdateMethod;

    }
}
