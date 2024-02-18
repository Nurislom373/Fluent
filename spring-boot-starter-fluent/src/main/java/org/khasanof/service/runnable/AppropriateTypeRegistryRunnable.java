package org.khasanof.service.runnable;

import org.khasanof.executors.appropriate.type.CallbackUpdateTypeMatcher;
import org.khasanof.executors.appropriate.type.InlineQueryUpdateTypeMatcher;
import org.khasanof.executors.appropriate.type.MessageUpdateTypeMatcher;
import org.khasanof.registry.appropriate.AppropriateTypeRegistryContainer;
import org.springframework.stereotype.Service;

/**
 * @author Nurislom
 * @see org.khasanof.service.runnable
 * @since 2/4/2024 10:19 PM
 */
@Service
public class AppropriateTypeRegistryRunnable implements PostProcessor {

    private final AppropriateTypeRegistryContainer appropriateTypeRegistryContainer;

    public AppropriateTypeRegistryRunnable(AppropriateTypeRegistryContainer appropriateTypeRegistryContainer) {
        this.appropriateTypeRegistryContainer = appropriateTypeRegistryContainer;
    }

    @Override
    public void run() {
        appropriateTypeRegistryContainer.addAppropriateType(new MessageUpdateTypeMatcher());
        appropriateTypeRegistryContainer.addAppropriateType(new CallbackUpdateTypeMatcher());
        appropriateTypeRegistryContainer.addAppropriateType(new InlineQueryUpdateTypeMatcher());
    }
}
