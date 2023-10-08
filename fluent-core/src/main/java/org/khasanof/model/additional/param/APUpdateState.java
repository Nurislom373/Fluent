package org.khasanof.model.additional.param;

import org.khasanof.enums.additional.AdditionalParamType;
import org.khasanof.model.AdditionalParam;
import org.khasanof.state.State;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof.model.additional.param
 * @since 8/20/2023 5:12 PM
 */
public interface APUpdateState extends AdditionalParam<Update, State> {

    @Override
    default Class<?> getReturnType() {
        return State.class;
    }

    @Override
    default AdditionalParamType getType() {
        return AdditionalParamType.STATE_PARAM;
    }

}
