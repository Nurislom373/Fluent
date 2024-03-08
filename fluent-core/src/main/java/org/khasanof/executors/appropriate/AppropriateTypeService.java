package org.khasanof.executors.appropriate;

import org.khasanof.models.executors.AppropriateType;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.executors.appropriate
 * @since 12/25/2023 12:16 AM
 */
public interface AppropriateTypeService {

    Optional<AppropriateType> getAppropriateType(Update update);

}
