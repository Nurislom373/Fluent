package org.khasanof.executors.invoker.additional.param.twts;

import org.khasanof.enums.additional.AdditionalParamType;
import org.khasanof.executors.invoker.additional.param.TWT;
import org.khasanof.model.InvokerModel;
import org.khasanof.utils.MethodUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.executors.invoker.additional.param.twts
 * @since 8/13/2023 7:09 PM
 */
@Component
public class TWTProcessFileParam implements TWT {

    @Override
    @SuppressWarnings("unchecked")
    public Object getValue(InvokerModel invokerModel, Object[] args, Method method) {
        return invokerModel.getAdditionalParam().getParam(MethodUtils.getArg(args, Update.class));
    }

    @Override
    public AdditionalParamType getType() {
        return AdditionalParamType.PROCESS_FILE_PARAM;
    }

}
