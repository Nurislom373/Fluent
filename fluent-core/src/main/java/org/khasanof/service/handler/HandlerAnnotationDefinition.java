package org.khasanof.service.handler;

/**
 * @author Nurislom
 * @see org.khasanof.service.handler
 * @since 2/19/2024 10:50 PM
 */
public interface HandlerAnnotationDefinition {

    /**
     *
     * @return
     */
    boolean isDefault();

    /**
     *
     * @return
     */
    boolean isRepeatable();

    /**
     *
     * @return
     */
    boolean isNewAppropriateUpdateType();

    /**
     *
     * @return
     */
    boolean isNewAppropriateUpdateMethod();
}
