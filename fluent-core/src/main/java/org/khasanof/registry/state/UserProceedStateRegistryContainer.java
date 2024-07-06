package org.khasanof.registry.state;

import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.registry.state
 * @since 6/8/2024 2:37 AM
 */
public interface UserProceedStateRegistryContainer {

    /**
     *
     * @param userId
     * @param isProceed
     */
    void add(String userId, Boolean isProceed);

    /**
     *
     * @param userId
     * @return
     */
    Optional<Boolean> getProceedState(String userId);
}
