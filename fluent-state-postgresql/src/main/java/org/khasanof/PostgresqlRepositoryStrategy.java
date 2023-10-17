package org.khasanof;

import org.khasanof.state.State;
import org.khasanof.state.repository.StateRepositoryStrategy;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 10/17/2023 9:40 PM
 */
@Repository
public class PostgresqlRepositoryStrategy implements StateRepositoryStrategy {

    private final DataSourceProperties dataSourceProperties;

    public PostgresqlRepositoryStrategy(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Override
    public Optional<State> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Map<Long, State> getStates() {
        return null;
    }

    @Override
    public void addState(Long id, State state) {

    }

    @Override
    public void addState(Long id) {

    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }
}
