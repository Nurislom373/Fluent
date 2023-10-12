package org.khasanof.state.repository;

import org.khasanof.state.State;

import java.util.Map;
import java.util.Optional;

/**
 * The StateRepositoryStrategy interface is used to store states in any one of several strategies.
 *
 * @author Nurislom
 * @see org.khasanof.state.repository
 * @since 10/7/2023 9:40 PM
 */
public interface StateRepositoryStrategy {

    /**
     * We can get the user state by this id and update the state.
     *
     * @param id this id parameter is equal to the userId from the update.
     * @return
     */
    Optional<State> findById(Long id);

    Map<Long, State> getStates();

    void addState(Long id, State state);

    void addState(Long id);

    boolean existById(Long id);

    long count();

}
