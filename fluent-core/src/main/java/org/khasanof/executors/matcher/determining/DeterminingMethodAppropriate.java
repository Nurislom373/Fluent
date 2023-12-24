package org.khasanof.executors.matcher.determining;

import org.khasanof.models.executors.AppropriateMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.executors
 * @since 12/24/2023 4:49 PM
 */
public interface DeterminingMethodAppropriate {

    Optional<AppropriateMethod> determining(Update update);

}
