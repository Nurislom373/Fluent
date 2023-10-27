package org.khasanof;

import org.khasanof.query.QueriesEnum;
import org.khasanof.query.QueryRepository;
import org.khasanof.state.State;
import org.khasanof.state.repository.StateRepositoryStrategy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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

    private final JdbcTemplate jdbcTemplate;
    private final StateRowMapper stateRowMapper;
    private final QueryRepository queryRepository;
    private final NamedParameterJdbcTemplate parameterJdbcTemplate;
    private final SqlParameterSourceComposite parameterSourceComposite;

    public PostgresqlRepositoryStrategy(NamedParameterJdbcTemplate parameterJdbcTemplate, DataSourceFactory dataSourceFactory,
                                        QueryRepository queryRepository, StateRowMapper stateRowMapper,
                                        SqlParameterSourceComposite parameterSourceComposite) {
        this.parameterJdbcTemplate = parameterJdbcTemplate;
        this.queryRepository = queryRepository;
        this.stateRowMapper = stateRowMapper;
        this.parameterSourceComposite = parameterSourceComposite;
        this.jdbcTemplate = new JdbcTemplate(dataSourceFactory.createDataSource());
    }

    @Override
    public Optional<State> findById(Long id) {
        return parameterJdbcTemplate.queryForObject(queryRepository.get(QueriesEnum.SELECT_ONE),
                parameterSourceComposite.create(QueriesEnum.SELECT_ONE, id), this.stateRowMapper);
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
