package org.khasanof.registry.appropriate;

import org.khasanof.enums.HandleType;
import org.khasanof.executors.appropriate.AppropriateUpdateMethod;

import java.util.*;

/**
 * @author Nurislom
 * @see org.khasanof.registry
 * @since 2/4/2024 8:40 PM
 */
@SuppressWarnings({"rawtypes"})
public class DefaultAppropriateMethodRegistryContainer implements AppropriateMethodRegistryContainer {

    private final Map<HandleType, List<AppropriateUpdateMethod>> methods = new HashMap<>();

    @Override
    public Map<HandleType, List<AppropriateUpdateMethod>> getAppropriateMethods() {
        return this.methods;
    }

    @Override
    public void addAppropriateMethods(List<AppropriateUpdateMethod> methods) {
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
            List<AppropriateUpdateMethod> appropriateUpdateMethods = methods.get(method.handleType());
            appropriateUpdateMethods.add(method);
            return;
        }
        methods.put(method.handleType(), new ArrayList<>(List.of(method)));
    }
}
