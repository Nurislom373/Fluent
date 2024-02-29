package org.khasanof;

import org.khasanof.state.configurer.StateConfigurer;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 8/15/2023 9:09 PM
 */
public interface StateConfigurerAdapter<T extends Enum> {

    /**
     *
     * @param state
     * @throws Exception
     */
    void configure(StateConfigurer<T> state) throws Exception;
}
