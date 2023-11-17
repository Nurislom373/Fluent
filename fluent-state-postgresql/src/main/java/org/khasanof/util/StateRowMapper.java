package org.khasanof.util;

import org.khasanof.state.State;
import org.khasanof.state.StateBuilder;
import org.khasanof.state.StateImpl;
import org.khasanof.state.configurer.StateConfigReader;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 10/27/2023 11:12 PM
 */
@Component
public class StateRowMapper implements RowMapper<State> {

    private final StateConfigReader configReader;

    public StateRowMapper(StateConfigReader configReader) {
        this.configReader = configReader;
    }

    @Override
    public State mapRow(ResultSet rs, int rowNum) throws SQLException {
        return stateBuilder(rs.getString("state"), rs.getLong("userId"));
    }

    private State stateBuilder(String state, Long userId) {
        return StateBuilder.create(Enum.valueOf(configReader.getStateType(), state), userId);
    }

}
