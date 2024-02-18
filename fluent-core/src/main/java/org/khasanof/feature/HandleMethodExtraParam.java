package org.khasanof.feature;

import org.khasanof.enums.DefaultMethodType;
import org.khasanof.models.invoker.SimpleInvoker;

/**
 * @author Nurislom
 * @see org.khasanof.feature
 * @since 2/1/2024 10:22 PM
 */
public interface HandleMethodExtraParam {

    void execute(SimpleInvoker simpleInvoker);

    DefaultMethodType methodType();

}
