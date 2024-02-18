package org.khasanof.service.processor;

import org.khasanof.executors.processor.AbstractUpdateChainProcessor;
import org.khasanof.factories.processor.CachedUpdateChainProcessorFactory;
import org.khasanof.service.interceptor.FluentInterceptorService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof.service.processor
 * @since 2/3/2024 6:58 PM
 */
@Service
public class DefaultUpdateChainProcessorService implements UpdateChainProcessorService {

    private final FluentInterceptorService fluentInterceptorService;
    private final CachedUpdateChainProcessorFactory updateChainProcessorFactory;

    public DefaultUpdateChainProcessorService(FluentInterceptorService fluentInterceptorService,
                                              CachedUpdateChainProcessorFactory updateChainProcessorFactory) {

        this.fluentInterceptorService = fluentInterceptorService;
        this.updateChainProcessorFactory = updateChainProcessorFactory;
    }

    @Override
    public void process(Update update) throws Exception {
        fluentInterceptorService.preIntercept(update);
        abstractProcessor(update);
        fluentInterceptorService.postIntercept(update);
    }

    private void abstractProcessor(Update update) throws Exception {
        AbstractUpdateChainProcessor processor = updateChainProcessorFactory.create();
        processor.process(update);
    }
}
