package org.khasanof;

import org.khasanof.factories.response.methods.SendAnswerCallbackQueryFirstMethodResponse;
import org.khasanof.factories.response.methods.SendTextSecondMethodResponse;
import org.khasanof.mediator.DefaultExeMethodResponseMediator;
import org.khasanof.mediator.ExecMethodResponseMediator;
import org.khasanof.factories.response.ExecMethodResponse;
import org.khasanof.factories.response.methods.SendTextFirstMethodResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/14/2024 12:33 AM
 */
@Configuration
public class ExecMethodResponseAdapterConfiguration {

    @Bean
    public ExecMethodResponseClasses execMethodResponseClasses() {
        return new ExecMethodResponseClasses();
    }

    @Bean
    @ConditionalOnClass(ExecMethodResponseClasses.class)
    public ExecMethodResponseMediator execMethodResponseAdapter(ExecMethodResponseClasses responseClasses) {
        var methodResponseAdapter = new DefaultExeMethodResponseMediator();
        methodResponseAdapter.setExecMethodResponseMap(responseClasses.getExecMethodResponses());
        return methodResponseAdapter;
    }

    public static class ExecMethodResponseClasses {

        public Collection<ExecMethodResponse> getExecMethodResponses() {
            return List.of(
                    new SendTextFirstMethodResponse(),
                    new SendTextSecondMethodResponse(),
                    new SendAnswerCallbackQueryFirstMethodResponse()
            );
        }
    }
}
