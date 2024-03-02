package org.khasanof;

import org.khasanof.context.FluentContextHolder;
import org.khasanof.entity.FltStateEntity;
import org.khasanof.entity.FltUserEntity;
import org.khasanof.repository.FltStateRepository;
import org.khasanof.repository.FltUserRepository;
import org.khasanof.state.State;
import org.khasanof.state.configurer.StateConfigurerReaderSingleton;
import org.khasanof.state.repository.StateRepositoryStrategy;
import org.khasanof.util.PostgresqlRepositoryStrategyHelper;
import org.khasanof.utils.UpdateUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 10/17/2023 9:40 PM
 */
@Primary
@Repository
public class PostgresqlRepositoryStrategy implements StateRepositoryStrategy {

    private final FltUserRepository fltUserRepository;
    private final FltStateRepository fltStateRepository;
    private final StateConfigurerReaderSingleton stateConfigReader;
    private final PostgresqlRepositoryStrategyHelper repositoryStrategyHelper;

    public PostgresqlRepositoryStrategy(FltUserRepository fltUserRepository,
                                        FltStateRepository fltStateRepository,
                                        StateConfigurerReaderSingleton stateConfigReader,
                                        PostgresqlRepositoryStrategyHelper repositoryStrategyHelper) {

        this.fltUserRepository = fltUserRepository;
        this.fltStateRepository = fltStateRepository;
        this.stateConfigReader = stateConfigReader;
        this.repositoryStrategyHelper = repositoryStrategyHelper;
    }

    @Override
    public Optional<State> findById(Long id) {
        return fltStateRepository.findTop1ByUser_UserId(id)
                .map(repositoryStrategyHelper::createState);
    }

    @Override
    public Map<Long, State> getStates() {
        return fltStateRepository.findAll()
                .stream()
                .map(fltStateEntity -> new AbstractMap.SimpleEntry<>(fltStateEntity.getUser().getUserId(),
                        repositoryStrategyHelper.createState(fltStateEntity)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public void addState(Long id) {
        addState(id, null);
    }

    @Override
    public void addState(Long id, State state) {
        FltStateEntity fltStateEntity = new FltStateEntity();
        fltStateEntity.setState(getState(state));
        fltStateEntity.setUser(getFltUserOrCreate(id));
        fltStateRepository.save(fltStateEntity);
    }

    private String getState(State state) {
        return Objects.isNull(state) ? getInitialState() : state.getState().toString();
    }

    private String getInitialState() {
        return stateConfigReader.getConfigReader().getInitial().toString();
    }

    private FltUserEntity getFltUserOrCreate(Long id) {
        return fltUserRepository.findFirstByUserId(id)
                .orElse(createNewUser());
    }

    private FltUserEntity createNewUser() {
        User user = UpdateUtils.getFrom(FluentContextHolder.getCurrentUpdate());
        if (user == null) {
            return null;
        }
        return fltUserRepository.save(repositoryStrategyHelper.userToFltUserEntity(user));
    }

    @Override
    public void removeState(Long id) {
        fltStateRepository.deleteByUser_UserId(id);
    }

    @Override
    public boolean existById(Long id) {
        return fltStateRepository.existsByUser_UserId(id);
    }

    @Override
    public long count() {
        return fltStateRepository.count();
    }
}
