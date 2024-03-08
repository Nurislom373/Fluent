package org.khasanof.registry.handle;

import org.khasanof.models.handle.HandleTypeFind;

import java.util.List;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.registry.handle
 * @since 2/18/2024 6:55 PM
 */
public interface HandleTypeFindRegistry {

    /**
     *
     * @return
     */
    Set<HandleTypeFind> getHandleTypeFinds();

    /**
     *
     * @param handleTypes
     */
    void addHandleTypeFinds(Set<HandleTypeFind> handleTypes);

    /**
     *
     * @param handleType
     */
    void addHandleTypeFind(HandleTypeFind handleType);
}
