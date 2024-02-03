package org.khasanof.executors.processor;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.constants.FluentConstants;
import org.khasanof.service.exception.ExceptionResolver;
import org.khasanof.service.exception.ExceptionResolverService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.executors.processor
 * @since 1/25/2024 10:32 PM
 */
@Slf4j
public class ExceptionChainProcessor extends AbstractUpdateChainProcessor {

    private final ExceptionResolverService exceptionResolver;

    public ExceptionChainProcessor(ExceptionResolverService exceptionResolver) {
        this.exceptionResolver = exceptionResolver;
    }

    @Override
    public void process(Update update) throws Exception {
         try {
             tryProcess(update);
         } catch (Exception ex) {
             catchException(update, ex);
         }
    }

    private void tryProcess(Update update) throws Exception {
        if (Objects.nonNull(nextProcessor)) {
            nextProcessor.process(update);
        }
    }

    private void catchException(Update update, Exception ex) throws Exception {
        exceptionResolver.resolve(new ExceptionResolver(update, ex));
    }

    @Override
    public int getOrder() {
        return FluentConstants.HIGH_ORDER;
    }
}
