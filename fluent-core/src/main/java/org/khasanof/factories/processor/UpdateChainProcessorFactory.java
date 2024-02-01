package org.khasanof.factories.processor;

import org.khasanof.GenericSetParam;
import org.khasanof.executors.processor.AbstractUpdateChainProcessor;
import org.khasanof.factories.NopGenericFactory;

/**
 * @author Nurislom
 * @see org.khasanof.factories.processor
 * @since 2/1/2024 9:49 PM
 */
public interface UpdateChainProcessorFactory extends NopGenericFactory<AbstractUpdateChainProcessor>, GenericSetParam<AbstractUpdateChainProcessor> {
}
