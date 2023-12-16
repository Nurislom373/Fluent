package org.khasanof.executors.invoker.additional.param;

import org.khasanof.annotation.methods.HandleMessage;
import org.khasanof.enums.additional.AdditionalParamType;
import org.khasanof.executors.invoker.param.TWT;
import org.khasanof.models.AdditionalParam;
import org.khasanof.models.Invoker;
import org.khasanof.utils.MethodUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.executors.invoker.additional.param
 * @since 8/13/2023 7:05 PM
 */
@Component
public class TWTVarExpressionParam implements TWT {

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Object getValue(Invoker invokerModel, Object[] args, Method method) {
        AdditionalParam additionalParam = invokerModel.getAdditionalParam();
        Annotation[] annotations = method.getDeclaredAnnotations();
        Annotation fAnn = Arrays.stream(annotations).filter(annotation -> annotation.annotationType()
                        .equals(HandleMessage.class))
                .findFirst().orElse(null);
        if (Objects.nonNull(fAnn)) {
            return additionalParam.getParam(Map.entry(MethodUtils.getArg(args, Update.class), fAnn));
        }
        return null;
    }

    @Override
    public AdditionalParamType getType() {
        return AdditionalParamType.VAR_EXPRESSION_PARAM;
    }

}
