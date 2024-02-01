package org.khasanof.service.exception;

import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof.service.exception
 * @since 2/1/2024 11:39 PM
 */
@Component
public class DefaultExceptionResolverService implements ExceptionResolverService {

    @Override
    public void resolve(ExceptionResolver resolver) throws Exception {
        throw new RuntimeException(resolver.getThrowable());
    }
}
