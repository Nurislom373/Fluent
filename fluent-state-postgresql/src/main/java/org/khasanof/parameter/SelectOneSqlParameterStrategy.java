package org.khasanof.parameter;

import org.khasanof.config.FluentPostgresqlConstants;
import org.khasanof.query.QueriesEnum;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Nurislom
 * @see org.khasanof.parameter
 * @since 10/27/2023 11:36 PM
 */
@Component
public class SelectOneSqlParameterStrategy implements SqlParameterStrategy {

    @Override
    public SqlParameterSource create(Object... args) {
        return new MapSqlParameterSource(Map.of(
                "table", FluentPostgresqlConstants.TABLE_NAME,
                "userId", args[0]
        ));
    }

    @Override
    public QueriesEnum queryType() {
        return QueriesEnum.SELECT_ONE;
    }
}
