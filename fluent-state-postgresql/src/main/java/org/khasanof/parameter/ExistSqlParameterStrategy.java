package org.khasanof.parameter;

import org.khasanof.config.FluentPostgresqlConstants;
import org.khasanof.query.QueriesEnum;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.parameter
 * @since 11/17/2023 11:19 PM
 */
@Service
public class ExistSqlParameterStrategy implements SqlParameterStrategy {

    @Override
    public SqlParameterSource create(Object... args) {
        return new MapSqlParameterSource(Map.of(
                "table", FluentPostgresqlConstants.TABLE_NAME,
                "value", args[0]
        ));
    }

    @Override
    public QueriesEnum queryType() {
        return QueriesEnum.EXIST;
    }
}
