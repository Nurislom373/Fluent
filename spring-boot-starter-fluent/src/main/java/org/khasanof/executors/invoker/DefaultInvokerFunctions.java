package org.khasanof.executors.invoker;

import org.jetbrains.annotations.NotNull;
import org.khasanof.executors.invoker.param.AdditionalParamServiceAdapter;
import org.khasanof.models.AdditionalParam;
import org.khasanof.models.Invoker;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.*;

import static org.khasanof.factories.invoker.HandleProcessFileFactory.HANDLE_UPDATE_PROCESS_FILE;

/**
 * @author Nurislom
 * @see org.khasanof.executors.invoker
 * @since 15.07.2023 23:59
 */
@Component(DefaultInvokerFunctions.NAME)
public class DefaultInvokerFunctions extends AbstractInvokerFunctions {

    public static final String NAME = "defaultInvokerFunctions";
    private final AdditionalParamServiceAdapter additionalParamService;

    public DefaultInvokerFunctions(AdditionalParamServiceAdapter additionalParamService) {
        this.additionalParamService = additionalParamService;
    }

    @Override
    public Invoker adaptee(SimpleInvoker simpleInvoker, Object... args) {
        Invoker invoker = findInvoker(simpleInvoker);
        fillInvokerArguments(simpleInvoker, args, invoker);
        return invoker;
    }

    private Invoker findInvoker(SimpleInvoker simpleInvoker) {
        return getAll().stream().filter(invoker -> invokerMatcher(invoker, simpleInvoker))
                .findFirst().orElseThrow(() -> new RuntimeException("InvokerModel not found!"));
    }

    private void fillInvokerArguments(SimpleInvoker simpleInvoker, Object[] args, Invoker modelV2) {
        modelV2.setArgs(getInvokerArguments(simpleInvoker, args, modelV2));
        modelV2.setInvokerReference(simpleInvoker);
    }

    @NotNull
    private Object[] getInvokerArguments(SimpleInvoker simpleInvoker, Object[] args, Invoker modelV2) {
        List<Object> objects = new ArrayList<>();
        if (modelV2.hasAdditionalParam() && !modelV2.isInputSystem()) {
            Method invokerMethod = simpleInvoker.getMethod();
            if (modelV2.getName().equals(HANDLE_UPDATE_PROCESS_FILE)) {
                int parameterCount = invokerMethod.getParameterCount();
                if (parameterCount > 2) {
                    objects.add(getAdditionalParamV2(modelV2, args, invokerMethod));
                }
            } else {
                objects.add(getAdditionalParamV2(modelV2, args, invokerMethod));
            }
        }
        objects.addAll(Arrays.asList(args));
        return objects.toArray();
    }

    @SuppressWarnings("unchecked")
    private boolean invokerMatcher(Invoker invoker, SimpleInvoker simpleInvoker) {
        return invoker.getCondition().match(simpleInvoker) && (!invoker.hasAdditionalChecks() ||
                invoker.getAdditionalChecks().check(simpleInvoker));
    }

    @SuppressWarnings({"rawtypes"})
    private Object getAdditionalParamV2(Invoker invoker, Object[] args, Method method) {
        AdditionalParam additionalParam = invoker.getAdditionalParam();
        return additionalParamService.getParam(additionalParam.getType(), invoker, args, method);
    }

}
