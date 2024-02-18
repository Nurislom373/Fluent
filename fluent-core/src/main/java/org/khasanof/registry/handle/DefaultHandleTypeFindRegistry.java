package org.khasanof.registry.handle;

import org.khasanof.models.handle.HandleTypeFind;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.registry.handle
 * @since 2/18/2024 6:58 PM
 */
public class DefaultHandleTypeFindRegistry implements HandleTypeFindRegistry {

    private final List<HandleTypeFind> handleTypes = new ArrayList<>();

    @Override
    public List<HandleTypeFind> getHandleTypeFinds() {
        return this.handleTypes;
    }

    @Override
    public void addHandleTypeFinds(List<HandleTypeFind> handleTypes) {
        if (Objects.nonNull(handleTypes)) {
            handleTypes.forEach(this::addHandleTypeFind);
        }
    }

    @Override
    public void addHandleTypeFind(HandleTypeFind handleType) {
        if (Objects.nonNull(handleType)) {
            this.handleTypes.add(handleType);
        }
    }
}
