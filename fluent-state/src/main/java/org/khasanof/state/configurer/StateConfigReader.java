package org.khasanof.state.configurer;

import org.khasanof.state.StateTransmit;

/**
 * @author Nurislom
 * @see org.khasanof.state.configurer
 * @since 10/7/2023 9:15 PM
 */
public interface StateConfigReader extends StateTransmit {

    Class getStateType();

}
