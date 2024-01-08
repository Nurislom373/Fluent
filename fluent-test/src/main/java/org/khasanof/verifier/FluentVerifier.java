package org.khasanof.verifier;

import org.khasanof.VerifierAssertions;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/7/2024 1:12 PM
 */
public interface FluentVerifier {

    VerifierAssertions create(Update update);

}
