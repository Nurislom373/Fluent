package org.khasanof.registry.appropriate;

import org.khasanof.executors.appropriate.AppropriateUpdateMethod;
import org.khasanof.models.executors.UpdateType;

import java.util.List;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.registry
 * @since 2/4/2024 8:41 PM
 */
public interface AppropriateMethodRegistryContainer {

    Map<UpdateType, List<AppropriateUpdateMethod>> getAppropriateMethods();

    void addAppropriateMethods(List<AppropriateUpdateMethod> methods);

    void addAppropriateMethod(AppropriateUpdateMethod method);
}
