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

    /**
     *
     * @return
     */
    Map<Long, State> getStates();

    /**
     *
     * @param id
     * @param state
     */
    void addState(Long id, State state);

    /**
     *
     * @param id
     */
    void addState(Long id);

    /**
     *
     * @param id
     */
    void removeState(Long id);

    /**
     *
     * @param id
     * @return
     */
    boolean existById(Long id);

    /**
     *
     * @return
     */
    long count();
}
