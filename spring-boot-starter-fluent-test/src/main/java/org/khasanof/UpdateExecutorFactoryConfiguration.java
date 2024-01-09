package org.khasanof;

import org.khasanof.executors.invoker.InvokerExecutor;
import org.khasanof.executors.invoker.InvokerFunctionsAdaptee;
import org.khasanof.factories.executor.DefaultUpdateExecutorFactory;
import org.khasanof.factories.executor.UpdateExecutorFactory;
import org.khasanof.simulate.SimulateDeterminationUpdate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/9/2024 9:07 PM
 */
@Configuration
@ConditionalOnClass({SimulateDeterminationUpdateConfiguration.class})
public class UpdateExecutorFactoryConfiguration {

    @Bean
    public UpdateExecutorFactory updateExecutorFactory(InvokerExecutor invokerExecutor,
                                                       InvokerFunctionsAdaptee invokerFunctionsAdaptee,
                                                       SimulateDeterminationUpdate determinationUpdate) {
        return new DefaultUpdateExecutorFactory(invokerExecutor, invokerFunctionsAdaptee, determinationUpdate);
    }

}
