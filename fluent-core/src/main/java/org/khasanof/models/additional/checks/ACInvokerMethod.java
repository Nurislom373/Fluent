package org.khasanof.models.additional.checks;

import org.khasanof.enums.additional.AdditionalParamType;
import org.khasanof.models.AdditionalChecks;
import org.khasanof.models.invoker.SimpleInvokerMethod;

/**
 * @author Nurislom
 * @see org.khasanof.models.additional
 * @since 8/20/2023 3:27 PM
 */
public interface ACInvokerMethod extends AdditionalChecks<SimpleInvokerMethod> {

    @Override
    default AdditionalParamType getType() {
        return AdditionalParamType.VAR_EXPRESSION_PARAM;
    }

}
