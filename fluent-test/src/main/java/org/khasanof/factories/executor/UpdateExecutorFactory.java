package org.khasanof.factories.executor;

import org.khasanof.executors.UpdateHandler;
import org.khasanof.service.processor.UpdateChainProcessorService;

/**
 * @author Nurislom
 * @see org.khasanof.factories
 * @since 1/9/2024 12:52 AM
 */
public interface UpdateExecutorFactory {

    UpdateHandler create(UpdateChainProcessorService service);

}
