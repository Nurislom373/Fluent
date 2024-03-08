package org.khasanof.service.annotation.type;

import org.khasanof.enums.HandleType;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.service.annotation.type
 * @since 2/17/2024 11:53 PM
 */
public interface HandleTypeService {

    /**
     *
     * @param update
     * @return
     */
    Optional<HandleType> findByUpdate(Update update);

    /**
     *
     * @param update
     * @return
     */
    List<HandleType> findAllByUpdate(Update update);
}
