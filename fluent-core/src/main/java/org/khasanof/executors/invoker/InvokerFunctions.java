package org.khasanof.executors.invoker;

import org.khasanof.models.Invoker;

import java.util.Optional;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.executors.invoker
 * @since 10/13/2023 11:40 PM
 */
public interface InvokerFunctions extends InvokerFunctionsAdaptee {

    void add(Invoker invoker);

    Optional<Invoker> findByName(String name);

    Set<Invoker> getAll();

}
