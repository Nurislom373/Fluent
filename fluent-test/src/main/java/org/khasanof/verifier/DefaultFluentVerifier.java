package org.khasanof.verifier;

import org.khasanof.DefaultVerifierAssertionsBuilder;
import org.khasanof.FluentBot;
import org.khasanof.VerifierAssertions;
import org.khasanof.executors.UpdateExecutor;
import org.khasanof.factories.ProxyFluentBotFactory;
import org.khasanof.factories.UpdateExecutorFactory;
import org.khasanof.memento.DefaultMethodInvokeHistory;
import org.khasanof.memento.MethodInvokeHistory;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof.verifier
 * @since 1/9/2024 12:32 AM
 */
public class DefaultFluentVerifier implements FluentVerifier {

    private final ProxyFluentBotFactory proxyFluentBotFactory;
    private final UpdateExecutorFactory updateExecutorFactory;

    public DefaultFluentVerifier(ProxyFluentBotFactory proxyFluentBotFactory,
                                 UpdateExecutorFactory updateExecutorFactory) {

        this.proxyFluentBotFactory = proxyFluentBotFactory;
        this.updateExecutorFactory = updateExecutorFactory;
    }

    @Override
    public VerifierAssertions create(Update update) {
        var history = createHistory();
        executeUpdate(update, history);
        return createDefaultVerifier(history);
    }

    private void executeUpdate(Update update, MethodInvokeHistory history) {
        FluentBot fluentBot = proxyFluentBotFactory.create(history);
        UpdateExecutor updateExecutor = updateExecutorFactory.create(fluentBot);
        updateExecutor.execute(update);
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
