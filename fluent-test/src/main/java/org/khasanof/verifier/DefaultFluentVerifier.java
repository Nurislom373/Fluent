package org.khasanof.verifier;

import org.khasanof.verifier.assertions.DefaultVerifierAssertionsBuilder;
import org.khasanof.FluentBot;
import org.khasanof.MainHandler;
import org.khasanof.verifier.assertions.VerifierAssertions;
import org.khasanof.executors.UpdateExecutor;
import org.khasanof.factories.proxy.ProxyFluentBotFactory;
import org.khasanof.factories.executor.UpdateExecutorFactory;
import org.khasanof.factories.executor.SimulateExecutorServiceFactory;
import org.khasanof.factories.handler.DefaultSimulateMainHandlerFactory;
import org.khasanof.factories.handler.SimulateMainHandlerFactory;
import org.khasanof.memento.DefaultMethodInvokeHistory;
import org.khasanof.memento.MethodInvokeHistory;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * @author Nurislom
 * @see org.khasanof.verifier
 * @since 1/9/2024 12:32 AM
 */
public class DefaultFluentVerifier implements FluentVerifier {

    private final ProxyFluentBotFactory proxyFluentBotFactory;
    private final UpdateExecutorFactory updateExecutorFactory;
    private final SimulateExecutorServiceFactory serviceFactory;

    public DefaultFluentVerifier(ProxyFluentBotFactory proxyFluentBotFactory,
                                 UpdateExecutorFactory updateExecutorFactory,
                                 SimulateExecutorServiceFactory serviceFactory) {

        this.proxyFluentBotFactory = proxyFluentBotFactory;
        this.updateExecutorFactory = updateExecutorFactory;
        this.serviceFactory = serviceFactory;
    }

    @Override
    public VerifierAssertions execute(Update update) {
        var history = createHistory();
        executeUpdate(update, history);
        return createDefaultVerifier(history);
    }

    private void executeUpdate(Update update, MethodInvokeHistory history) {
        try {
           tryExecuteUpdate(update, history);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void tryExecuteUpdate(Update update, MethodInvokeHistory history) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        FluentBot fluentBot = proxyFluentBotFactory.create(history);
        UpdateExecutor updateExecutor = updateExecutorFactory.create(fluentBot);
        ExecutorService executorService = serviceFactory.create(countDownLatch);
        SimulateMainHandlerFactory handlerFactory = new DefaultSimulateMainHandlerFactory(executorService);
        MainHandler handler = handlerFactory.create(updateExecutor);
        handler.process(update);
        countDownLatch.await();
    }

    private MethodInvokeHistory createHistory() {
        return new DefaultMethodInvokeHistory();
    }

    private VerifierAssertions createDefaultVerifier(MethodInvokeHistory methodInvokeHistory) {
        return new DefaultVerifierAssertionsBuilder()
                .methodInvokerHistory(methodInvokeHistory)
                .build();
    }
}
