package org.khasanof.executors.invoker.param;

import org.khasanof.enums.additional.AdditionalParamType;
import org.khasanof.models.Invoker;
import org.khasanof.utils.MethodUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.executors.invoker.param
 * @since 8/20/2023 5:15 PM
 */
@Component
public class AdditionalStateParamService implements AdditionalParamService {

    @Override
    @SuppressWarnings("unchecked")
    public Object getValue(Invoker invokerModel, Object[] args, Method method) {
        return invokerModel.getAdditionalParam().getParam(MethodUtils.getArg(args, Update.class));
    }

    @Override
    public AdditionalParamType getType() {
        return AdditionalParamType.STATE_PARAM;
    }
}
