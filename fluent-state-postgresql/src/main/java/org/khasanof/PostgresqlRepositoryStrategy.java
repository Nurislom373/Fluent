package org.khasanof;

import org.khasanof.config.FluentPostgresqlConstants;
import org.khasanof.parameter.SqlParameterSourceAdapter;
import org.khasanof.query.QueriesEnum;
import org.khasanof.query.QueryRepository;
import org.khasanof.state.State;
import org.khasanof.state.StateWithId;
import org.khasanof.state.configurer.StateConfigReader;
import org.khasanof.state.repository.StateRepositoryStrategy;
import org.khasanof.util.StateRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.khasanof.util.FluentQueryUtils.createParams;
import static org.khasanof.util.FluentQueryUtils.existUtil;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 10/17/2023 9:40 PM
 */
@Repository
public class PostgresqlRepositoryStrategy implements StateRepositoryStrategy {

    private final StateRowMapper stateRowMapper;
    private final StateConfigReader configReader;
    private final QueryRepository queryRepository;
    private final NamedParameterJdbcTemplate parameterJdbcTemplate;
    private final SqlParameterSourceAdapter parameterSourceAdapter;

    public PostgresqlRepositoryStrategy(NamedParameterJdbcTemplate parameterJdbcTemplate, StateConfigReader configReader,
                                        QueryRepository queryRepository, StateRowMapper stateRowMapper,
                                        SqlParameterSourceAdapter parameterSourceAdapter) {
        this.parameterJdbcTemplate = parameterJdbcTemplate;
        this.configReader = configReader;
        this.queryRepository = queryRepository;
        this.stateRowMapper = stateRowMapper;
        this.parameterSourceAdapter = parameterSourceAdapter;
    }

    @Override
    public Optional<State> findById(Long id) {
        return Optional.ofNullable(parameterJdbcTemplate.queryForObject(queryRepository.get(QueriesEnum.SELECT_ONE),
                parameterSourceAdapter.create(QueriesEnum.SELECT_ONE, id), this.stateRowMapper));
    }

    @Override
    public Map<Long, State> getStates() {
        return parameterJdbcTemplate.query(queryRepository.get(QueriesEnum.SELECT_ALL),
                        parameterSourceAdapter.create(QueriesEnum.SELECT_ALL), this.stateRowMapper)
                .stream().map(this::stateConvertToEntry)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public void addState(Long id, State state) {
        parameterJdbcTemplate.execute(queryRepository.get(QueriesEnum.INSERT),
                createParams(FluentPostgresqlConstants.TABLE_NAME, id, state), PreparedStatement::execute);
    }

    @Override
    public void addState(Long id) {
        parameterJdbcTemplate.execute(queryRepository.get(QueriesEnum.INSERT),
                createParams(FluentPostgresqlConstants.TABLE_NAME, id, configReader.getInitial().name()),
                PreparedStatement::execute);
    }

    @Override
    public boolean existById(Long id) {
        return existUtil(parameterJdbcTemplate.queryForObject(queryRepository.get(QueriesEnum.EXIST),
                parameterSourceAdapter.create(QueriesEnum.EXIST, id), Long.class));
    }

    @Override
    @SuppressWarnings("all")
    public long count() {
        return parameterJdbcTemplate.queryForObject(queryRepository.get(QueriesEnum.COUNT),
                parameterSourceAdapter.create(QueriesEnum.COUNT), Long.class);
    }

    private AbstractMap.SimpleEntry<Long, State> stateConvertToEntry(State state) {
        return new AbstractMap.SimpleEntry<>(((StateWithId) state).getUserId(), state);
    }

}
