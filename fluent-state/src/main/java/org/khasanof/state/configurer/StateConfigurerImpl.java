package org.khasanof.state.configurer;

import org.springframework.util.Assert;

import java.util.EnumSet;

/**
 * @author Nurislom
 * @see org.khasanof.state.configurer
 * @since 10/7/2023 9:12 PM
 */
public class StateConfigurerImpl implements StateConfigurer<Enum>, StateConfigReader {

    private Enum initial;
    private EnumSet states;

    @Override
    public StateConfigurer<Enum> initial(Enum initial) {
        Assert.notNull(initial, "initial state must not be null!");
        this.initial = initial;
        return this;
    }

    @Override
    public StateConfigurer<Enum> states(EnumSet states) {
        Assert.notNull(states, "states must not be null!");
        this.states = states;
        return this;
    }

    @Override
    public Enum getInitial() {
        return this.initial;
    }

    @Override
    public EnumSet getStates() {
        return this.states;
    }

}
