package org.khasanof.registry.binder;

import org.khasanof.feature.binder.UpdateTypeBinder;

import java.util.List;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.registry.binder
 * @since 2/18/2024 4:58 PM
 */
public interface UpdateTypeBinderRegistry {

    /**
     *
     * @return
     */
    Set<UpdateTypeBinder> getUpdateTypeBinders();

    /**
     *
     * @param binders
     */
    void addUpdateTypeBinders(Set<UpdateTypeBinder> binders);

    /**
     *
     * @param binder
     */
    void addUpdateTypeBinder(UpdateTypeBinder binder);
}
