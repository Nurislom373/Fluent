package org.khasanof.util;

import org.jetbrains.annotations.NotNull;
import org.khasanof.entity.FltStateHash;
import org.khasanof.entity.FltUserHash;
import org.khasanof.state.FltState;
import org.khasanof.state.State;
import org.khasanof.state.configurer.StateConfigurerReaderSingleton;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

/**
 * @author Nurislom
 * @see org.khasanof.util
 * @since 3/16/2024 8:15 PM
 */
@Service
public class RedisRepositoryStrategyHelper {

    private final ApplicationEventPublisher eventPublisher;
    private final StateConfigurerReaderSingleton configReader;

    public RedisRepositoryStrategyHelper(ApplicationEventPublisher eventPublisher, StateConfigurerReaderSingleton configReader) {
        this.eventPublisher = eventPublisher;
        this.configReader = configReader;
    }

    /**
     *
     * @param fltState
     * @return
     */
    public State createState(FltStateHash fltState) {
        return new FltState(getState(fltState), fltState.getId(), fltState.getUserId(), eventPublisher);
    }

    @NotNull
    private Enum getState(FltStateHash fltState) {
        return Enum.valueOf(configReader.getConfigReader().getStateType(), fltState.getState());
    }

    /**
     *
     * @param user
     * @return
     */
    public FltUserHash userToFltUserHash(User user) {
        FltUserHash fltUserHash = new FltUserHash();
        fltUserHash.setLastname(user.getLastName());
        fltUserHash.setUsername(user.getUserName());
        fltUserHash.setFirstname(user.getFirstName());
        fltUserHash.setUserId((user.getId()));
        fltUserHash.setLanguageCode(user.getLanguageCode());
        fltUserHash.setSupportInlineQueries(user.getSupportInlineQueries());
        return fltUserHash;
    }
}
