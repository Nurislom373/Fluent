package org.khasanof.models.additional.param;

import org.khasanof.enums.additional.AdditionalParamType;
import org.khasanof.models.AdditionalParam;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof.models.additional.param
 * @since 8/10/2023 8:05 AM
 */
public interface APUpdateObject extends AdditionalParam<Update, Object> {

    @Override
    default Class<?> getReturnType() {
        return Object.class;
    }

    @Override
    default AdditionalParamType getType() {
        return AdditionalParamType.PROCESS_FILE_PARAM;
    }

}