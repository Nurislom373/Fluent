package org.khasanof.executors.appropriate;

import org.khasanof.models.executors.AppropriateMethod;
import org.khasanof.models.executors.AppropriateType;

import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.executors.appropriate
 * @since 12/25/2023 12:17 AM
 */
public interface AppropriateMethodService {

    Optional<AppropriateMethod> getAppropriateMethod(AppropriateType appropriateType);

}
