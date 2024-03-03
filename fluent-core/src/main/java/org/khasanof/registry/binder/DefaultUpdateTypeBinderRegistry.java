package org.khasanof.registry.binder;

import org.khasanof.feature.binder.UpdateTypeBinder;

import java.util.*;

/**
 * @author Nurislom
 * @see org.khasanof.registry.binder
 * @since 2/18/2024 4:59 PM
 */
public class DefaultUpdateTypeBinderRegistry implements UpdateTypeBinderRegistry {

    private final Set<UpdateTypeBinder> binders = new HashSet<>();

    @Override
    public Set<UpdateTypeBinder> getUpdateTypeBinders() {
        return this.binders;
    }

    @Override
    public void addUpdateTypeBinders(Set<UpdateTypeBinder> binders) {
        if (Objects.nonNull(binders)) {
            this.binders.addAll(binders);
        }
    }

    @Override
    public void addUpdateTypeBinder(UpdateTypeBinder binder) {
        if (Objects.nonNull(binder)) {
            this.binders.add(binder);
        }
    }
}
