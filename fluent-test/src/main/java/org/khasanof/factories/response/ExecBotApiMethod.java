package org.khasanof.factories.response;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

/**
 * @author Nurislom
 * @see org.khasanof.factories.response
 * @since 1/13/2024 11:53 PM
 */
public interface ExecBotApiMethod {

    Class<? extends BotApiMethod> botApiMethod();

}
