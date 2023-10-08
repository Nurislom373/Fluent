package org.khasanof.model.additional.param;

import org.khasanof.enums.additional.AdditionalParamType;
import org.khasanof.model.AdditionalParam;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.model.additional
 * @since 8/10/2023 8:03 AM
 */
public interface APAnnotationMap extends AdditionalParam<Map.Entry<Update, Annotation>, Map<String, String>> {

    @Override
    default Class<?> getReturnType() {
        return Map.class;
    }

    @Override
    default AdditionalParamType getType() {
        return AdditionalParamType.VAR_EXPRESSION_PARAM;
    }

}
