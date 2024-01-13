package org.khasanof;

import org.khasanof.adapter.DefaultExeMethodResponseAdapter;
import org.khasanof.adapter.ExecMethodResponseAdapter;
import org.khasanof.factories.response.ExecMethodResponse;
import org.khasanof.factories.response.methods.ExecSendMessageMethodResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;
import java.util.Map;

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
    public ExecMethodResponseAdapter execMethodResponseAdapter(ExecMethodResponseClasses responseClasses) {
        var methodResponseAdapter = new DefaultExeMethodResponseAdapter();
        methodResponseAdapter.setExecMethodResponseMap(responseClasses.getExecMethodResponses());
        return methodResponseAdapter;
    }

    public static class ExecMethodResponseClasses {

        public Map<Class<? extends BotApiMethod>, ExecMethodResponse> getExecMethodResponses() {
            return new HashMap<>() {{
                put(SendMessage.class, new ExecSendMessageMethodResponse());
            }};
        }
    }
}
