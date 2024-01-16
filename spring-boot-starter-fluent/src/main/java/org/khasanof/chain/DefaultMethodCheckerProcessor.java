package org.khasanof.chain;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.chain
 * @since 1/17/2024 12:43 AM
 */
public class DefaultMethodCheckerProcessor extends AbstractChainProcessor<Method, Boolean> {

    public DefaultMethodCheckerProcessor(GenericChainProcessor<Method, Boolean> nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public Boolean process(Method method) {
        return null;
    }
}
