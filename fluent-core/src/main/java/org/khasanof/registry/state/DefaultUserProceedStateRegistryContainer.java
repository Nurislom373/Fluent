package org.khasanof.registry.state;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Nurislom
 * @see org.khasanof.registry.state
 * @since 6/8/2024 2:38 AM
 */
public class DefaultUserProceedStateRegistryContainer implements UserProceedStateRegistryContainer {

    private final Map<String, Boolean> proceedStateContext = new ConcurrentHashMap<>();

    /**
     *
     * @param userId
     * @param isProceed
     */
    @Override
    public void add(String userId, Boolean isProceed) {
        this.proceedStateContext.put(userId, isProceed);
    }

    /**
     *
     * @param userId
     * @return
     */
    @Override
    public Optional<Boolean> getProceedState(String userId) {
        return Optional.ofNullable(this.proceedStateContext.get(userId));
    }
}
