package org.khasanof.state;

import org.khasanof.state.configurer.StateConfigurer;

/**
 * @author Nurislom
 * @see org.khasanof.state
 * @since 8/15/2023 9:09 PM
 */
public interface StateConfigurerAdapter<T extends Enum> {

    void configure(StateConfigurer<T> state) throws Exception;

}
