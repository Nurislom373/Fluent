package org.khasanof.executors.processor;

import org.khasanof.executors.determination.DeterminationFunction;

/**
 * @author Nurislom
 * @see org.khasanof.executors.processor
 * @since 2/1/2024 11:13 PM
 */
public interface ProcessorBinding {

    Class<? extends AbstractUpdateChainProcessor> processor();

    Class<? extends DeterminationFunction> determination();

}
