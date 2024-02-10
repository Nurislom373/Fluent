package org.khasanof.factories.executor;

import org.khasanof.executors.DefaultUpdateHandler;
import org.khasanof.executors.UpdateHandler;
import org.khasanof.service.processor.UpdateChainProcessorService;

/**
 * @author Nurislom
 * @see org.khasanof.factories
 * @since 1/9/2024 12:54 AM
 */
public class DefaultUpdateExecutorFactory implements UpdateExecutorFactory {

    @Override
    public UpdateHandler create(UpdateChainProcessorService service) {
        return new DefaultUpdateHandler(service);
    }
}
