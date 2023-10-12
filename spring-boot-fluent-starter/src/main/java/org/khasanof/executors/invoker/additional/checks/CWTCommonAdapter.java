package org.khasanof.executors.invoker.additional.checks;

import org.khasanof.model.InvokerModel;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof.executors.invoker.additional.checks
 * @since 8/13/2023 8:06 PM
 */
@Component(CWTCommonAdapter.NAME)
public class CWTCommonAdapter {

    public static final String NAME = "cwtCommonAdapter";

    @SuppressWarnings("unchecked")
    public boolean check(InvokerModel invokerModel) {
        return invokerModel.getAdditionalChecks().check(invokerModel);
    }
}
