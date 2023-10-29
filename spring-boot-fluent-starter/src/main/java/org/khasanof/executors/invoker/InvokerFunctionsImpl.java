package org.khasanof.executors.invoker;

import org.khasanof.executors.invoker.param.TWTCommonAdapter;
import org.khasanof.model.AdditionalParam;
import org.khasanof.model.SampleModel;
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

    private final Set<SampleModel> invokerModelV2s = new LinkedHashSet<>();
    private final TWTCommonAdapter twtCommonAdapter;
    private final InvokerResultService resultService;

    public InvokerFunctionsImpl(TWTCommonAdapter twtCommonAdapter, InvokerResultService resultService) {
        this.twtCommonAdapter = twtCommonAdapter;
        this.resultService = resultService;
    }

    @Override
    public void add(SampleModel modelV2) {
        invokerModelV2s.add(modelV2);
    }

    @Override
    public SampleModel fillAndGet(InvokerResult result, Object... args) {
        SampleModel modelV2 = invokerModelV2s.stream().filter(invokerModelV2 -> result.getType().equals(invokerModelV2.getType()) &&
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
    public SampleModel findByName(String name) {
        return invokerModelV2s.parallelStream().filter(invokerModelV2 -> invokerModelV2.getName().equals(name))
                .findFirst().orElseThrow(() -> new RuntimeException("invoker model not found"));
    }

    @SuppressWarnings("unchecked")
    private boolean invokerModelV2Matcher(SampleModel modelV2, InvokerResult result) {
        if (!modelV2.hasAdditionalChecks()) {
            return modelV2.getCondition().match(result);
        } else {
            boolean condition;
            condition = modelV2.getCondition().match(result);
            return condition && modelV2.getAdditionalChecks().check(result);
        }
    }

    @SuppressWarnings({"rawtypes"})
    private Object getAdditionalParamV2(SampleModel invokerModel, Object[] args, Method method) {
        AdditionalParam additionalParam = invokerModel.getAdditionalParam();
        return twtCommonAdapter.takeParam(additionalParam.getType(), invokerModel, args, method);
    }
}
