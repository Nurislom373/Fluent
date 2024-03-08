package org.khasanof.verifier.assertions;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/8/2024 10:41 PM
 */
public interface VerifierAssertions {

    VerifierAssertions expectSendText();

    VerifierAssertions expectSendText(String message);

    VerifierAssertions expectSendTextCount(long count);

    VerifierAssertions expectSendAnswerCallbackQuery();

    VerifierAssertions expectSendAnswerCallbackQuery(String callbackData);

    VerifierAssertions expectSendAnswerCallbackQueryCount(long count);
}
