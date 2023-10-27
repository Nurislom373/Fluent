package org.khasanof.parameter;

import org.khasanof.query.QueriesEnum;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

/**
 * @author Nurislom
 * @see org.khasanof.parameter
 * @since 10/27/2023 11:35 PM
 */
public interface SqlParameterStrategy {

    SqlParameterSource create(Object... args);

    QueriesEnum queryType();

}
