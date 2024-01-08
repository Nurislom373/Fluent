package org.khasanof.factories;

import org.khasanof.FluentBot;
import org.khasanof.executors.UpdateExecutor;

/**
 * @author Nurislom
 * @see org.khasanof.factories
 * @since 1/9/2024 12:52 AM
 */
public interface UpdateExecutorFactory {

    UpdateExecutor create(FluentBot fluentBot);

}
