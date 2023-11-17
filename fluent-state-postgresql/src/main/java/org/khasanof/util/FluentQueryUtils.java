package org.khasanof.util;

import org.khasanof.config.FluentPostgresqlConstants;
import org.khasanof.state.State;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 11/17/2023 11:04 PM
 */
public abstract class FluentQueryUtils {

    public static SqlParameterSource defaultParameterSource() {
        return new MapSqlParameterSource(Map.of("table", FluentPostgresqlConstants.TABLE_NAME));
    }

    public static boolean existUtil(Long count) {
        return count > 0;
    }

    public static Map<String, ?> createParams(String tableName, Long userId, State state) {
        return Map.of(
                "table", tableName,
                "userId", userId,
                "state", state.getState().name()
        );
    }

    public static Map<String, ?> createParams(String tableName, Long userId, String state) {
        return Map.of(
                "table", tableName,
                "userId", userId,
                "state", state
        );
    }

}
