package org.khasanof.state.configurer;

import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.state.configurer
 * @since 3/2/2024 11:27 AM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DefaultStateConfigurerReaderSingleton implements StateConfigurerReaderSingleton {

    private StateConfigReader configReader;

}
