package org.khasanof.executors.appropriate;

import org.khasanof.models.executors.AppropriateType;
import org.khasanof.registry.appropriate.AppropriateTypeRegistryContainer;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.executors.appropriate
 * @since 12/25/2023 12:23 AM
 */
public class DefaultAppropriateTypeService implements AppropriateTypeService {

    private final AppropriateTypeRegistryContainer registryContainer;

    public DefaultAppropriateTypeService(AppropriateTypeRegistryContainer registryContainer) {
        this.registryContainer = registryContainer;
    }

    @Override
    public Optional<AppropriateType> getAppropriateType(Update update) {
        return registryContainer.getAppropriateTypes()
                .stream()
                .filter(type -> type.isMatch(update))
                .map(type -> type.getAppropriate(update))
                .findFirst();
    }
}
