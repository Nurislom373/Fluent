package org.khasanof.model.additional.checks;

import org.khasanof.enums.additional.AdditionalParamType;
import org.khasanof.model.AdditionalChecks;
import org.khasanof.model.InvokerModel;

/**
 * @author Nurislom
 * @see org.khasanof.model.additional
 * @since 8/13/2023 6:49 PM
 */
public interface ACInvokerModel extends AdditionalChecks<InvokerModel> {

    @Override
    default AdditionalParamType getType() {
        return AdditionalParamType.VAR_EXPRESSION_PARAM;
    }

}
