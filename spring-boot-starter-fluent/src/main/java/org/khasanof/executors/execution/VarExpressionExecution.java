package org.khasanof.executors.execution;

import org.khasanof.annotation.expression.BotVariable;
import org.khasanof.enums.additional.AdditionalParamType;
import org.khasanof.event.ExecutionMethod;
import org.khasanof.models.invoker.SimpleInvoker;
import org.khasanof.utils.MethodUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Nurislom
 * @see org.khasanof.executors.execution
 * @since 8/13/2023 9:42 PM
 */
@Component
public class VarExpressionExecution extends AbstractExecution {

    @Override
    @SuppressWarnings("unchecked")
    public void run(ExecutionMethod executionMethod) throws InvocationTargetException, IllegalAccessException {
        SimpleInvoker simpleInvoker = executionMethod.getInvokerModel().getInvokerReference();
        AdditionalParamType paramType = executionMethod.getInvokerModel().getAdditionalParam().getType();
        Object[] args = executionMethod.getInvokerModel().getArgs();

        Object stringMap = Arrays.stream(executionMethod.getInvokerModel().getArgs())
                .filter(o -> o.getClass().equals(paramType.getParmaType()))
                .findFirst().orElseThrow(() -> new RuntimeException("Match object not found!"));

        Object[] objects = mapGetValues((Map<String, String>) stringMap, simpleInvoker.getMethod());
        int length = executionMethod.getInvokerModel().getArgs().length;

        Object[] array = Arrays.stream(args).filter(o -> !o.getClass().equals(HashMap.class)).toArray();
        Object[] copy = Arrays.copyOf(array, length + objects.length);
        System.arraycopy(objects, 0, copy, length, objects.length);

        getInvokerMethod(simpleInvoker).invoke(simpleInvoker.getReference(), MethodUtils.cleanerV2(copy));
    }

    private Object[] mapGetValues(Map<String, String> stringMap, Method method) {
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
        return variables.toArray();
    }

    @Override
    public AdditionalParamType getType() {
        return AdditionalParamType.VAR_EXPRESSION_PARAM;
    }
}
