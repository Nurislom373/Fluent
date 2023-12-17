package org.khasanof.factories.invoker;

import org.khasanof.SortOrder;
import org.khasanof.factories.NoParamsGenericFactory;
import org.khasanof.models.Invoker;

import static org.khasanof.constants.FluentConstants.DEFAULT_ORDER;

/**
 * @author Nurislom
 * @see org.khasanof.factories.invoker
 * @since 12/17/2023 2:58 PM
 */
public interface InvokerFactory extends NoParamsGenericFactory<Invoker>, SortOrder {

    @Override
    default int getOrder() {
        return DEFAULT_ORDER;
    }

}
