package org.khasanof.state.repository;

import org.khasanof.state.State;
import org.khasanof.state.StateImpl;
import org.khasanof.state.collector.StateConfigCollector;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Nurislom
 * @see org.khasanof.state.repository
 * @since 10/7/2023 10:23 PM
 */
@Service(DefaultStateRepositoryStrategy.NAME)
public class DefaultStateRepositoryStrategy implements StateRepositoryStrategy {

    public static final String NAME = "defaultStateRepositoryStrategy";
    private final Map<Long, State> stateMap = new ConcurrentHashMap<>();
    private final StateConfigCollector configCollector;

    public DefaultStateRepositoryStrategy(StateConfigCollector configCollector) {
        this.configCollector = configCollector;
    }

    @Override
    public Optional<State> findById(Long id) {
        return Optional.ofNullable(stateMap.get(id));
    }

    @Override
    public Map<Long, State> getStates() {
        return this.stateMap;
    }

    @Override
    public void addState(Long id, State state) {
        this.stateMap.put(id, state);
    }

    @Override
    public void addState(Long id) {
        this.stateMap.put(id, new StateImpl(configCollector.getInitial()));
    }

    @Override
    public boolean existById(Long id) {
        return this.stateMap.containsKey(id);
    }

    @Override
    public long count() {
        return this.stateMap.size();
    }

}
