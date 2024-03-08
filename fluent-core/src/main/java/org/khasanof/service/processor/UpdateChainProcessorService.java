package org.khasanof.service.processor;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof.service.processor
 * @since 2/1/2024 9:46 PM
 */
public interface UpdateChainProcessorService {

    /**
     *
     * @param update
     * @throws Exception
     */
    void process(Update update) throws Exception;

}
