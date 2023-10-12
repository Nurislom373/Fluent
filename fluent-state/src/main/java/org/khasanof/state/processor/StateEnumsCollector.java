package org.khasanof.state.processor;

import org.khasanof.ObjectCollector;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.state.processor
 * @since 8/18/2023 5:46 AM
 */
@Component(StateEnumsCollector.NAME)
public class StateEnumsCollector implements ObjectCollector<Class<? extends Enum>> {

    public static final String NAME = "stateEnumsCollector";

    public final Set<Class<? extends Enum>> enums = new HashSet<>();

    @Override
    public Set<Class<? extends Enum>> getAll() {
        return this.enums;
    }

    @Override
    public void addAll(Set<Class<? extends Enum>> enums) {
        this.enums.addAll(enums);
    }

}
