package org.khasanof;

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
public class StateRowMapper implements RowMapper<Optional<State>> {

    private final StateConfigReader configReader;

    public StateRowMapper(StateConfigReader configReader) {
        this.configReader = configReader;
    }

    @Override
    public Optional<State> mapRow(ResultSet rs, int rowNum) throws SQLException {
        String stateColumn = rs.getString("state");
        if (Objects.nonNull(stateColumn)) {
            return Optional.of(stateBuilder(stateColumn));
        }
        return Optional.empty();
    }

    private State stateBuilder(String state) {
        return StateBuilder.create(Enum.valueOf(configReader.getStateType(), state));
    }

}
