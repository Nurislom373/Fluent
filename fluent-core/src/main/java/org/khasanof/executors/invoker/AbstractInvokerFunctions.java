package org.khasanof.executors.invoker;

import org.khasanof.models.Invoker;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.executors.invoker
 * @since 12/17/2023 5:03 PM
 */
public abstract class AbstractInvokerFunctions implements InvokerFunctions {

    private final Set<Invoker> invokers = new LinkedHashSet<>();

    @Override
    public void add(Invoker invoker) {
        this.invokers.add(invoker);
    }

    @Override
    public Set<Invoker> getAll() {
        return this.invokers;
    }

    @Override
    public Optional<Invoker> findByName(String name) {
        return this.invokers.stream().filter(invokerModelV2 -> invokerModelV2.getName().equals(name))
                .findFirst();
    }

}
