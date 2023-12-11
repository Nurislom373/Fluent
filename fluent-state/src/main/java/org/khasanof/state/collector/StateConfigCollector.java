package org.khasanof.state.collector;

import org.khasanof.GenericContains;
import org.khasanof.state.StateTransmit;

import java.util.EnumSet;

/**
 * @author Nurislom
 * @see org.khasanof.state.configurer
 * @since 10/7/2023 9:21 PM
 */
public interface StateConfigCollector extends StateTransmit, GenericContains<Enum> {

    void addInitial(Enum initial);

    void addStates(EnumSet states);

}
