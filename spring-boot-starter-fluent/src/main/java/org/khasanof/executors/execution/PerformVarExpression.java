package org.khasanof.executors.execution;

import org.khasanof.annotation.expression.BotVariable;
import org.khasanof.context.FluentContextHolder;
import org.khasanof.enums.DefaultMethodType;
import org.khasanof.models.invoker.InvokerParam;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Nurislom
 * @see org.khasanof.executors.execution
 * @since 2/1/2024 10:58 PM
 */
@Component
@SuppressWarnings({"unchecked"})
public class PerformVarExpression extends AbstractPerform {

    @Override
    void fillParams(SimpleInvoker simpleInvoker, List<Object> params) {
        checkSimpleInvoker(simpleInvoker, params);
        params.addAll(mapGetValues(getAdditionalParam(simpleInvoker), simpleInvoker.getMethod()));
    }

    private void checkSimpleInvoker(SimpleInvoker simpleInvoker, List<Object> params) {
        if (invokerMethodFirstParamIsUpdate(simpleInvoker.getMethod())) {
            params.add(FluentContextHolder.getCurrentFluentUpdate().getUpdate());
        }
    }

    private boolean invokerMethodFirstParamIsUpdate(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        return Objects.equals(parameterTypes[0], Update.class);
    }

    private Map<String, String> getAdditionalParam(SimpleInvoker simpleInvoker) {
        return (Map<String, String>) simpleInvoker.getParams().get(InvokerParam.ADDITIONAL_PARAM);
    }

    private List<String> mapGetValues(Map<String, String> stringMap, Method method) {
        List<String> variables = new ArrayList<>();

        Arrays.stream(method.getParameterAnnotations()).forEach(annotations -> {
            Annotation fAnn = Arrays.stream(annotations)
                    .filter(annotation -> annotation.annotationType().equals(BotVariable.class))
                    .findFirst().orElse(null);
            if (Objects.nonNull(fAnn)) {
                BotVariable botVariable = (BotVariable) fAnn;
                String var = stringMap.get(botVariable.value());
                if (Objects.nonNull(var)) {
                    variables.add(var);
                }
            }
        });
        return variables;
    }

    @Override
    public DefaultMethodType getType() {
        return DefaultMethodType.VAR_EXPRESSION;
    }
}
