package org.khasanof.registry.appropriate;

import org.khasanof.executors.appropriate.AppropriateUpdateType;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Nurislom
 * @see org.khasanof.registry
 * @since 2/4/2024 10:10 PM
 */
public class DefaultAppropriateTypeRegistryContainer implements AppropriateTypeRegistryContainer {

    private final List<AppropriateUpdateType> types = new CopyOnWriteArrayList<>();

    @Override
    public List<AppropriateUpdateType> getAppropriateTypes() {
        return this.types;
    }

    @Override
    public void addAppropriateTypes(List<AppropriateUpdateType> types) {
        if (Objects.nonNull(types)) {
            types.forEach(this::addAppropriateType);
        }
    }

    @Override
    public void addAppropriateType(AppropriateUpdateType type) {
        if (Objects.nonNull(type)) {
            this.types.add(type);
        }
    }
}
