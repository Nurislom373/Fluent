package org.khasanof.parameter;

import org.khasanof.query.QueriesEnum;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import static org.khasanof.util.FluentQueryUtils.defaultParameterSource;

/**
 * @author Nurislom
 * @see org.khasanof.parameter
 * @since 11/17/2023 11:25 PM
 */
@Service
public class CountSqlParameterStrategy implements SqlParameterStrategy {

    @Override
    public SqlParameterSource create(Object... args) {
        return defaultParameterSource();
    }

    @Override
    public QueriesEnum queryType() {
        return QueriesEnum.COUNT;
    }
}
