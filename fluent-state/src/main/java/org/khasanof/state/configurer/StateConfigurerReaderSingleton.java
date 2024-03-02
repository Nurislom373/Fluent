package org.khasanof.state.configurer;

/**
 * @author Nurislom
 * @see org.khasanof.state.configurer
 * @since 3/2/2024 11:26 AM
 */
public interface StateConfigurerReaderSingleton {

    /**
     *
     * @return {@link StateConfigReader} bean
     */
    StateConfigReader getConfigReader();

    /**
     *
     * @param reader
     */
    void setConfigReader(StateConfigReader reader);
}
