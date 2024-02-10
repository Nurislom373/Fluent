package org.khasanof.verifier;

import org.khasanof.UpdateHandlerManager;
import org.khasanof.executors.UpdateHandler;
import org.khasanof.factories.executor.SimulateExecutorServiceFactory;
import org.khasanof.factories.executor.UpdateExecutorFactory;
import org.khasanof.factories.handler.DefaultSimulateUpdateHandlerManagerFactory;
import org.khasanof.factories.handler.SimulateUpdateHandlerManagerFactory;
import org.khasanof.factories.proxy.ProxyFluentTemplateFactory;
import org.khasanof.memento.DefaultMethodInvokeHistory;
import org.khasanof.memento.MethodInvokeHistory;
import org.khasanof.service.ReinitializeFluentTemplateService;
import org.khasanof.service.processor.UpdateChainProcessorService;
import org.khasanof.verifier.assertions.DefaultVerifierAssertionsBuilder;
import org.khasanof.verifier.assertions.VerifierAssertions;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * @author Nurislom
 * @see org.khasanof.verifier
 * @since 1/9/2024 12:32 AM
 */
public class DefaultFluentVerifier implements FluentVerifier {

    private final UpdateExecutorFactory updateExecutorFactory;
    private final SimulateExecutorServiceFactory serviceFactory;
    private final UpdateChainProcessorService updateChainProcessorService;
    private final ReinitializeFluentTemplateService reinitializeFluentTemplateService;

    public DefaultFluentVerifier(UpdateExecutorFactory updateExecutorFactory,
                                 SimulateExecutorServiceFactory serviceFactory,
                                 UpdateChainProcessorService updateChainProcessorService,
                                 ReinitializeFluentTemplateService reinitializeFluentTemplateService) {

        this.serviceFactory = serviceFactory;
        this.updateExecutorFactory = updateExecutorFactory;
        this.updateChainProcessorService = updateChainProcessorService;
        this.reinitializeFluentTemplateService = reinitializeFluentTemplateService;
    }

    @Override
    public VerifierAssertions execute(Update update) {
        executeUpdate(update);
        var invokeHistory = reinitializeFluentTemplateService.getInvokeHistory();
        return createDefaultVerifier(invokeHistory);
    }

    private void executeUpdate(Update update) {
        try {
           tryExecuteUpdate(update);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void tryExecuteUpdate(Update update) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        UpdateHandler updateHandler = updateExecutorFactory.create(updateChainProcessorService);
        ExecutorService executorService = serviceFactory.create(countDownLatch);

        SimulateUpdateHandlerManagerFactory handlerFactory = new DefaultSimulateUpdateHandlerManagerFactory(executorService);
        UpdateHandlerManager handler = handlerFactory.create(updateHandler);
        handler.process(update);
        countDownLatch.await();
    }

    private VerifierAssertions createDefaultVerifier(MethodInvokeHistory methodInvokeHistory) {
        return new DefaultVerifierAssertionsBuilder()
                .methodInvokerHistory(methodInvokeHistory)
                .build();
    }
}
