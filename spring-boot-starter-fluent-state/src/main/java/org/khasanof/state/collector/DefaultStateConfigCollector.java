package org.khasanof.state.collector;

import org.springframework.stereotype.Component;

import java.util.EnumSet;

/**
 * @author Nurislom
 * @see org.khasanof.state.collector
 * @since 10/7/2023 9:27 PM
 */
@Component(DefaultStateConfigCollector.NAME)
public class DefaultStateConfigCollector implements StateConfigCollector {

    public static final String NAME = "defaultStateConfigCollector";

    private Enum initial;
    private EnumSet states;

    @Override
    public Enum getInitial() {
        return this.initial;
    }

    @Override
    public EnumSet getStates() {
        return this.states;
    }

    @Override
    public void addInitial(Enum initial) {
        this.initial = initial;
    }

    @Override
    public void addStates(EnumSet states) {
        this.states = states;
    }

    @Override
    public boolean contains(Enum state) {
        return this.states.contains(state);
    }
}
