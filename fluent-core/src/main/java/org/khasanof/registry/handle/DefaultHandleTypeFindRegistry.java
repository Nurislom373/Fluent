package org.khasanof.registry.handle;

import org.khasanof.models.handle.HandleTypeFind;

import java.util.*;

/**
 * @author Nurislom
 * @see org.khasanof.registry.handle
 * @since 2/18/2024 6:58 PM
 */
public class DefaultHandleTypeFindRegistry implements HandleTypeFindRegistry {

    private final Set<HandleTypeFind> handleTypes = new HashSet<>();

    @Override
    public Set<HandleTypeFind> getHandleTypeFinds() {
        return this.handleTypes;
    }

    @Override
    public void addHandleTypeFinds(Set<HandleTypeFind> handleTypes) {
        if (Objects.nonNull(handleTypes)) {
            this.handleTypes.addAll(handleTypes);
        }
    }

    @Override
    public void addHandleTypeFind(HandleTypeFind handleType) {
        if (Objects.nonNull(handleType)) {
            this.handleTypes.add(handleType);
        }
    }
}
