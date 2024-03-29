package org.khasanof.state.configurer;

import java.util.EnumSet;

/**
 * @author Nurislom
 * @see org.khasanof.state
 * @since 10/7/2023 9:00 PM
 */
public interface StateConfigurer<T extends Enum> {

    /**
     *
     * @param e
     * @return
     */
    StateConfigurer<T> initial(T e);

    /**
     *
     * @param ts
     * @return
     */
    StateConfigurer<T> states(EnumSet ts);
}
