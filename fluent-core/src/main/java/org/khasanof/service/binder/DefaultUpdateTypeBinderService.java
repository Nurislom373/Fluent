package org.khasanof.service.binder;

import org.khasanof.feature.UpdateTypeBinder;
import org.khasanof.models.executors.UpdateType;
import org.khasanof.registry.binder.UpdateTypeBinderRegistry;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.service.binder
 * @since 2/18/2024 5:02 PM
 */
public class DefaultUpdateTypeBinderService implements UpdateTypeBinderService {

    private final UpdateTypeBinderRegistry binderRegistry;

    public DefaultUpdateTypeBinderService(UpdateTypeBinderRegistry binderRegistry) {
        this.binderRegistry = binderRegistry;
    }

    @Override
    public Optional<UpdateTypeBinder> findByUpdateType(UpdateType type) {
        return binderRegistry.getUpdateTypeBinders()
                .stream()
                .filter(binder -> Objects.equals(binder.getUpdateType(), type))
                .findFirst();
    }
}
