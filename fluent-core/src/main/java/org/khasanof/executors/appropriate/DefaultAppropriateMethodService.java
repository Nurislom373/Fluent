package org.khasanof.executors.appropriate;

import org.khasanof.models.executors.AppropriateMethod;
import org.khasanof.models.executors.AppropriateType;
import org.khasanof.registry.appropriate.AppropriateMethodRegistryContainer;

import java.util.Collections;
import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.executors.appropriate
 * @since 12/25/2023 12:31 AM
 */
@SuppressWarnings({"unchecked"})
public class DefaultAppropriateMethodService implements AppropriateMethodService {

    private final AppropriateMethodRegistryContainer methodRegistryContainer;

    public DefaultAppropriateMethodService(AppropriateMethodRegistryContainer methodRegistryContainer) {
        this.methodRegistryContainer = methodRegistryContainer;
    }

    @Override
    public Optional<AppropriateMethod> getAppropriateMethod(AppropriateType appropriateType) {
        return methodRegistryContainer.getAppropriateMethods()
                .getOrDefault(appropriateType.getType(), Collections.emptySet())
                .stream()
                .filter(appropriateUpdateMethod -> {
                    return appropriateUpdateMethod.isMatch(appropriateType.getValue());
                }).map(appropriateUpdateMethod -> {
                    return appropriateUpdateMethod.getAppropriate(appropriateType.getValue());
                }).findFirst();
    }
}
