package org.khasanof.registry.appropriate;

import org.khasanof.executors.appropriate.AppropriateUpdateType;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.registry
 * @since 2/4/2024 10:08 PM
 */
public interface AppropriateTypeRegistryContainer {

    List<AppropriateUpdateType> getAppropriateTypes();

    void addAppropriateTypes(List<AppropriateUpdateType> types);

    void addAppropriateType(AppropriateUpdateType type);
}
