package org.khasanof.registry.appropriate;

import org.khasanof.executors.appropriate.AppropriateUpdateMethod;
import org.khasanof.models.executors.UpdateType;

import java.util.*;

/**
 * @author Nurislom
 * @see org.khasanof.registry
 * @since 2/4/2024 8:40 PM
 */
@SuppressWarnings({"rawtypes"})
public class DefaultAppropriateMethodRegistryContainer implements AppropriateMethodRegistryContainer {

    private final Map<UpdateType, Set<AppropriateUpdateMethod>> methods = new HashMap<>();

    @Override
    public Map<UpdateType, Set<AppropriateUpdateMethod>> getAppropriateMethods() {
        return this.methods;
    }

    @Override
    public void addAppropriateMethods(Set<AppropriateUpdateMethod> methods) {
        if (Objects.nonNull(methods) && !methods.isEmpty()) {
            methods.forEach(this::addAppropriateMethod);
        }
    }

    @Override
    public void addAppropriateMethod(AppropriateUpdateMethod method) {
        if (Objects.nonNull(method)) {
            internalAddAppropriateMethod(method);
        }
    }

    private void internalAddAppropriateMethod(AppropriateUpdateMethod method) {
        if (methods.containsKey(method.handleType())) {
            Set<AppropriateUpdateMethod> appropriateUpdateMethods = methods.get(method.handleType());
            appropriateUpdateMethods.add(method);
            return;
        }
        methods.put(method.handleType(), new HashSet<>(List.of(method)));
    }
}
