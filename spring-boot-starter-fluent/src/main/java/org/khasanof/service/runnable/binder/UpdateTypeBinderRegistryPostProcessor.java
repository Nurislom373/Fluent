package org.khasanof.service.runnable.binder;

import org.khasanof.annotation.methods.HandleCallback;
import org.khasanof.feature.binder.DefaultUpdateTypeBinder;
import org.khasanof.models.executors.UpdateType;
import org.khasanof.registry.binder.UpdateTypeBinderRegistry;
import org.khasanof.service.runnable.PostProcessor;
import org.springframework.stereotype.Service;

/**
 * @author Nurislom
 * @see org.khasanof.service.runnable.binder
 * @since 2/19/2024 12:23 AM
 */
@Service
public class UpdateTypeBinderRegistryPostProcessor implements PostProcessor {

    private final UpdateTypeBinderRegistry binderRegistry;

    public UpdateTypeBinderRegistryPostProcessor(UpdateTypeBinderRegistry binderRegistry) {
        this.binderRegistry = binderRegistry;
    }

    @Override
    public void run() {
        binderRegistry.addUpdateTypeBinder(new DefaultUpdateTypeBinder(HandleCallback.class, UpdateType.CALLBACK));
    }
}
