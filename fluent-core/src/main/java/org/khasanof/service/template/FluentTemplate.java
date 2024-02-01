package org.khasanof.service.template;

import org.khasanof.service.template.operations.SendDocumentOperations;
import org.khasanof.service.template.operations.SendTextOperations;

/**
 * @author Nurislom
 * @see org.khasanof.service.features
 * @since 1/23/2024 9:23 PM
 */
public interface FluentTemplate extends SendTextOperations, SendDocumentOperations {
}
