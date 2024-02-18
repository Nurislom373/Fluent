package org.khasanof.registry.binder;

import org.khasanof.feature.UpdateTypeBinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.registry.binder
 * @since 2/18/2024 4:59 PM
 */
public class DefaultUpdateTypeBinderRegistry implements UpdateTypeBinderRegistry {

    private final List<UpdateTypeBinder> binders = new ArrayList<>();

    @Override
    public List<UpdateTypeBinder> getUpdateTypeBinders() {
        return this.binders;
    }

    @Override
    public void addUpdateTypeBinders(List<UpdateTypeBinder> binders) {
        if (Objects.nonNull(binders)) {
            binders.forEach(this::addUpdateTypeBinder);
        }
    }

    @Override
    public void addUpdateTypeBinder(UpdateTypeBinder binder) {
        if (Objects.nonNull(binder)) {
            this.binders.add(binder);
        }
    }
}
