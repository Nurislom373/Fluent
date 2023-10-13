package org.khasanof.executors.invoker;

import org.khasanof.executors.invoker.additional.param.TWTCommonAdapter;
import org.khasanof.model.AdditionalParam;
import org.khasanof.model.InvokerModelV2;
import org.khasanof.model.InvokerResult;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.*;

import static org.khasanof.executors.invoker.DefaultInvokerFunctions.HANDLE_UPDATE_W_PROCESS_FL;

/**
 * @author Nurislom
 * @see org.khasanof.executors.invoker
 * @since 15.07.2023 23:59
 */
@Component
public class InvokerFunctionsImpl implements InvokerFunctions {

    private final Set<InvokerModelV2> invokerModelV2s = new LinkedHashSet<>();
    private final TWTCommonAdapter twtCommonAdapter;
    private final InvokerResultService resultService;

    public InvokerFunctionsImpl(TWTCommonAdapter twtCommonAdapter, InvokerResultService resultService) {
        this.twtCommonAdapter = twtCommonAdapter;
        this.resultService = resultService;
    }

    @Override
    public void add(InvokerModelV2 modelV2) {
        invokerModelV2s.add(modelV2);
    }

    @Override
    public InvokerModelV2 fillAndGet(InvokerResult result, Object... args) {
        InvokerModelV2 modelV2 = invokerModelV2s.stream().filter(invokerModelV2 -> result.getType().equals(invokerModelV2.getType()) &&
                        invokerModelV2Matcher(invokerModelV2, result))
                .findFirst().orElseThrow(() -> new RuntimeException("InvokerModel not found!"));

        List<Object> objects = new ArrayList<>();
        if (modelV2.hasAdditionalParam() && !modelV2.isInputSystem()) {
            Map.Entry<Method, Object> entry = resultService.getResultEntry(result);
            if (modelV2.getName().equals(HANDLE_UPDATE_W_PROCESS_FL)) {
                int parameterCount = entry.getKey().getParameterCount();
                if (parameterCount > 2) {
                    objects.add(getAdditionalParamV2(modelV2, args, entry.getKey()));
                }
            } else {
                objects.add(getAdditionalParamV2(modelV2, args, entry.getKey()));
            }
        }

        objects.addAll(Arrays.asList(args));
        modelV2.setArgs(objects.toArray());
        modelV2.setInvokerReference(result);
        return modelV2;
    }

    @Override
    public InvokerModelV2 findByName(String name) {
        return invokerModelV2s.parallelStream().filter(invokerModelV2 -> invokerModelV2.getName().equals(name))
                .findFirst().orElseThrow(() -> new RuntimeException("invoker model not found"));
    }

    @SuppressWarnings("unchecked")
    private boolean invokerModelV2Matcher(InvokerModelV2 modelV2, InvokerResult result) {
        if (!modelV2.hasAdditionalChecks()) {
            return modelV2.getCondition().match(result);
        } else {
            boolean condition;
            condition = modelV2.getCondition().match(result);
            return condition && modelV2.getAdditionalChecks().check(result);
        }
    }

    private Object getAdditionalParamV2(InvokerModelV2 invokerModel, Object[] args, Method method) {
        AdditionalParam additionalParam = invokerModel.getAdditionalParam();
        return twtCommonAdapter.takeParam(additionalParam.getType(), invokerModel, args, method);
    }
}
