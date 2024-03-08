package org.khasanof.service.exception;

/**
 * @author Nurislom
 * @see org.khasanof.service.exception
 * @since 1/27/2024 6:26 PM
 */
public interface ExceptionResolverService {

    /**
     *
     * @param resolver
     * @throws Exception
     */
    void resolve(ExceptionResolver resolver) throws Exception;
}
