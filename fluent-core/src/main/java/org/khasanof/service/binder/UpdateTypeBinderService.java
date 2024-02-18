package org.khasanof.service.binder;

import org.khasanof.feature.UpdateTypeBinder;
import org.khasanof.models.executors.UpdateType;

import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.service.binder
 * @since 2/18/2024 5:01 PM
 */
public interface UpdateTypeBinderService {

    /**
     *
     * @param type
     * @return
     */
    Optional<UpdateTypeBinder> findByUpdateType(UpdateType type);
}
