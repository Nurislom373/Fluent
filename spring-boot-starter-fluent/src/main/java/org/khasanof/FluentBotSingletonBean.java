package org.khasanof;

import org.khasanof.context.singleton.GenericSingleton;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.bots.AbsSender;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 12/10/2023 12:45 PM
 */
@Component(FluentBotSingletonBean.NAME)
public class FluentBotSingletonBean extends GenericSingleton<AbsSender> {

    /**
     * {@link FluentBotSingletonBean} name
     */
    public static final String NAME = "fluentBotSingletonBean";
}
