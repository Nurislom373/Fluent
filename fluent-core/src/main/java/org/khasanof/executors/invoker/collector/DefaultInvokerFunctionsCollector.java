package org.khasanof.executors.invoker.collector;

import org.khasanof.executors.invoker.InvokerFunctions;
import org.khasanof.factories.invoker.InvokerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Comparator;

/**
 * @author Nurislom
 * @see org.khasanof.executors.invoker
 * @since 12/17/2023 4:06 PM
 */
@Component
public class DefaultInvokerFunctionsCollector implements InvokerFunctionsCollector {

    private final InvokerFunctions invokerFunctions;
    private final ApplicationContext applicationContext;

    public DefaultInvokerFunctionsCollector(InvokerFunctions invokerFunctions, ApplicationContext applicationContext) {
        this.invokerFunctions = invokerFunctions;
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
        applicationContext.getBeansOfType(InvokerFactory.class)
                .values().stream()
                .sorted(Comparator.comparing(InvokerFactory::getOrder))
                .forEach(this::createInvokerFunction);
    }

    private void createInvokerFunction(InvokerFactory invokerFactory) {
        invokerFunctions.add(invokerFactory.create());
    }

}
