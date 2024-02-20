package org.khasanof.registry.appropriate;

import org.khasanof.executors.appropriate.AppropriateUpdateType;

import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.registry
 * @since 2/4/2024 10:08 PM
 */
public interface AppropriateTypeRegistryContainer {

    Set<AppropriateUpdateType> getAppropriateTypes();

    void addAppropriateTypes(Set<AppropriateUpdateType> types);

    void addAppropriateType(AppropriateUpdateType type);
}
