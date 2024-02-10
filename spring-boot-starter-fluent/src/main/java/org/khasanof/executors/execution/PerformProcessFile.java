package org.khasanof.executors.execution;

import org.khasanof.context.FluentContextHolder;
import org.khasanof.enums.MethodType;
import org.khasanof.models.invoker.InvokerParam;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.executors.execution
 * @since 2/3/2024 3:42 PM
 */
@Component
public class PerformProcessFile extends AbstractPerform {

    @Override
    void fillParams(SimpleInvoker simpleInvoker, List<Object> params) {
        if (!simpleInvoker.getParams().containsKey(InvokerParam.ADDITIONAL_PARAM)) {
            params.add(FluentContextHolder.getCurrentUpdate().getUpdate());
            return;
        }
        internalFillParams(simpleInvoker, params);
    }

    private void internalFillParams(SimpleInvoker simpleInvoker, List<Object> params) {
        Arrays.stream(simpleInvoker.getMethod().getParameterTypes())
                .forEach(parameterType -> matchThenAdd(simpleInvoker, params, parameterType));
    }

    private void matchThenAdd(SimpleInvoker simpleInvoker, List<Object> params, Class<?> parameterType) {
        if (Objects.equals(parameterType, InputStream.class)) {
            params.add(simpleInvoker.getParams().get(InvokerParam.ADDITIONAL_PARAM));
            return;
        }

        if (Objects.equals(parameterType, Update.class)) {
            params.add(FluentContextHolder.getCurrentUpdate().getUpdate());
        }
    }

    @Override
    public MethodType getType() {
        return MethodType.PROCESS_FILE;
    }
}
