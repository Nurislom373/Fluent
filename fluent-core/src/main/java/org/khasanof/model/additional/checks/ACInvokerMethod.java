package org.khasanof.model.additional.checks;

import org.khasanof.enums.additional.AdditionalParamType;
import org.khasanof.model.AdditionalChecks;
import org.khasanof.model.InvokerMethod;

/**
 * @author Nurislom
 * @see org.khasanof.model.additional
 * @since 8/20/2023 3:27 PM
 */
public interface ACInvokerMethod extends AdditionalChecks<InvokerMethod> {

    @Override
    default AdditionalParamType getType() {
        return AdditionalParamType.VAR_EXPRESSION_PARAM;
    }

}
