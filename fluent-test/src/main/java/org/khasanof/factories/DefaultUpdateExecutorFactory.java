package org.khasanof.factories;

import jakarta.validation.constraints.NotNull;
import org.khasanof.FluentBot;
import org.khasanof.context.singleton.GenericSingleton;
import org.khasanof.executors.UpdateExecutor;
import org.khasanof.executors.invoker.InvokerExecutor;
import org.khasanof.executors.invoker.InvokerFunctionsAdaptee;
import org.khasanof.simulate.SimulateDeterminationUpdate;
import org.khasanof.simulate.SimulateUpdateExecutor;

/**
 * @author Nurislom
 * @see org.khasanof.factories
 * @since 1/9/2024 12:54 AM
 */
public class DefaultUpdateExecutorFactory implements UpdateExecutorFactory {

    private final InvokerExecutor invoker;
    private final InvokerFunctionsAdaptee invokerFunctionsAdaptee;
    private final SimulateDeterminationUpdate simulateDeterminationUpdate;

    public DefaultUpdateExecutorFactory(InvokerExecutor invoker,
                                        InvokerFunctionsAdaptee invokerFunctionsAdaptee,
                                        SimulateDeterminationUpdate simulateDeterminationUpdate) {

        this.invoker = invoker;
        this.invokerFunctionsAdaptee = invokerFunctionsAdaptee;
        this.simulateDeterminationUpdate = simulateDeterminationUpdate;
    }

    @Override
    public UpdateExecutor create(FluentBot fluentBot) {
        return new SimulateUpdateExecutor(invoker, getGenericSingleton(fluentBot), invokerFunctionsAdaptee, simulateDeterminationUpdate);
    }

    @NotNull
    private GenericSingleton<FluentBot> getGenericSingleton(FluentBot fluentBot) {
        return new GenericSingleton<>() {
            @Override
            public FluentBot getInstance() {
                return fluentBot;
            }
        };
    }
}
