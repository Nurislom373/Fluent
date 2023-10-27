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
        List<Object> list = Arrays.asList(args);
        if (!list.isEmpty() && Objects.nonNull(list.get(0))) {
            Map<String, Object> params = new HashMap<>();
            params.put("table", FluentPostgresqlConstants.TABLE_NAME);
            params.put("user_id", list.get(0));
            return new MapSqlParameterSource(params);
        }
        throw new RuntimeException("args must not be null!");
    }

    @Override
    public QueriesEnum queryType() {
        return QueriesEnum.SELECT_ONE;
    }
}
