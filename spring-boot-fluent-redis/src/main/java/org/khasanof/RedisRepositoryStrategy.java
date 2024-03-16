package org.khasanof;

import org.khasanof.context.FluentContextHolder;
import org.khasanof.entity.FltStateHash;
import org.khasanof.entity.FltUserHash;
import org.khasanof.repository.FltStateRepository;
import org.khasanof.repository.FltUserRepository;
import org.khasanof.state.State;
import org.khasanof.state.configurer.StateConfigurerReaderSingleton;
import org.khasanof.state.repository.StateRepositoryStrategy;
import org.khasanof.util.RedisRepositoryStrategyHelper;
import org.khasanof.utils.UpdateUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 3/16/2024 8:00 PM
 */
@Primary
@Repository
public class RedisRepositoryStrategy implements StateRepositoryStrategy {

    private final FltUserRepository fltUserRepository;
    private final FltStateRepository fltStateRepository;
    private final StateConfigurerReaderSingleton stateConfigReader;
    private final RedisRepositoryStrategyHelper repositoryStrategyHelper;

    public RedisRepositoryStrategy(FltUserRepository fltUserRepository,
                                   FltStateRepository fltStateRepository,
                                   StateConfigurerReaderSingleton stateConfigReader,
                                   RedisRepositoryStrategyHelper repositoryStrategyHelper) {

        this.fltUserRepository = fltUserRepository;
        this.fltStateRepository = fltStateRepository;
        this.stateConfigReader = stateConfigReader;
        this.repositoryStrategyHelper = repositoryStrategyHelper;
    }

    @Override
    public Optional<State> findById(Long id) {
        return fltStateRepository.findTop1ByUserId(id)
                .map(repositoryStrategyHelper::createState);
    }

    @Override
    public Map<Long, State> getStates() {
        return StreamSupport.stream(fltStateRepository.findAll().spliterator(), false)
                .map(fltStateHash -> new AbstractMap.SimpleEntry<>(fltStateHash.getUserId(),
                        repositoryStrategyHelper.createState(fltStateHash)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public void addState(Long id) {
        addState(id, null);
    }

    @Override
    public void addState(Long id, State state) {
        FltStateHash fltStateHash = new FltStateHash();
        fltStateHash.setState(getState(state));
        fltStateHash.setUserId(getUserOrCreate(id));
        fltStateRepository.save(fltStateHash);
    }

    private String getState(State state) {
        return Objects.isNull(state) ? getInitialState() : state.getState().toString();
    }

    private String getInitialState() {
        return stateConfigReader.getConfigReader().getInitial().toString();
    }

    private Long getUserOrCreate(Long id) {
        FltUserHash fltUserHash = fltUserRepository.findFirstByUserId(id)
                .orElse(createNewUser());

        if (Objects.isNull(fltUserHash)) {
            return null;
        }
        return fltUserHash.getUserId();
    }

    private FltUserHash createNewUser() {
        User user = UpdateUtils.getFrom(FluentContextHolder.getCurrentUpdate());
        if (user == null) {
            return null;
        }
        return fltUserRepository.save(repositoryStrategyHelper.userToFltUserHash(user));
    }

    @Override
    public void removeState(Long id) {
        fltStateRepository.deleteByUserId(id);
    }

    @Override
    public boolean existById(Long id) {
        return fltStateRepository.existsByUserId(id);
    }

    @Override
    public long count() {
        return fltStateRepository.count();
    }
}
