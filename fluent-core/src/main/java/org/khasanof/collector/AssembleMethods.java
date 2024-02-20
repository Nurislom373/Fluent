package org.khasanof.collector;

import org.khasanof.SortOrder;
import org.khasanof.constants.FluentConstants;

/**
 * @author Nurislom
 * @see org.khasanof.collector
 * @since 8/19/2023 12:24 AM
 */
public interface AssembleMethods extends SortOrder {

    /**
     *
     */
    void assemble();

    /**
     *
     * @return
     */
    @Override
    default int getOrder() {
        return FluentConstants.DEFAULT_ORDER;
    }
}
