package org.khasanof.registry.appropriate;

import org.khasanof.executors.appropriate.AppropriateUpdateType;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Nurislom
 * @see org.khasanof.registry
 * @since 2/4/2024 10:10 PM
 */
public class DefaultAppropriateTypeRegistryContainer implements AppropriateTypeRegistryContainer {

    private final Set<AppropriateUpdateType> types = new HashSet<>();

    @Override
    public Set<AppropriateUpdateType> getAppropriateTypes() {
        return this.types;
    }

    @Override
    public void addAppropriateTypes(Set<AppropriateUpdateType> types) {
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
