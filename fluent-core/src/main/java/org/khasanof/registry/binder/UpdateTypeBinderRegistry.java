package org.khasanof.registry.binder;

import org.khasanof.feature.UpdateTypeBinder;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.registry.binder
 * @since 2/18/2024 4:58 PM
 */
public interface UpdateTypeBinderRegistry {

    List<UpdateTypeBinder> getUpdateTypeBinders();

    void addUpdateTypeBinders(List<UpdateTypeBinder> binders);

    void addUpdateTypeBinder(UpdateTypeBinder binder);
}
