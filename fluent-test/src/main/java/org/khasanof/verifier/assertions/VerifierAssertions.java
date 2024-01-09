package org.khasanof.verifier.assertions;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/8/2024 10:41 PM
 */
public interface VerifierAssertions {

    VerifierAssertions expectSendMessage();

    VerifierAssertions expectSendMessage(String message);

    VerifierAssertions expectSendMessageCount(long count);

}
