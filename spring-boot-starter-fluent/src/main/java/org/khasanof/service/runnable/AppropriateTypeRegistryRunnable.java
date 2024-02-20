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
    private final MessageUpdateTypeMatcher messageUpdateTypeMatcher;

    public AppropriateTypeRegistryRunnable(AppropriateTypeRegistryContainer appropriateTypeRegistryContainer,
                                           MessageUpdateTypeMatcher messageUpdateTypeMatcher) {

        this.appropriateTypeRegistryContainer = appropriateTypeRegistryContainer;
        this.messageUpdateTypeMatcher = messageUpdateTypeMatcher;
    }

    @Override
    public void run() {
        appropriateTypeRegistryContainer.addAppropriateType(messageUpdateTypeMatcher);
        appropriateTypeRegistryContainer.addAppropriateType(new CallbackUpdateTypeMatcher());
        appropriateTypeRegistryContainer.addAppropriateType(new InlineQueryUpdateTypeMatcher());
    }
}
