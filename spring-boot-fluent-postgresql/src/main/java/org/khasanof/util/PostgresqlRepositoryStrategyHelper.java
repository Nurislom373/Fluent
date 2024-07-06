package org.khasanof.util;

import org.jetbrains.annotations.NotNull;
import org.khasanof.entity.FltStateEntity;
import org.khasanof.entity.FltUserEntity;
import org.khasanof.state.FltState;
import org.khasanof.state.State;
import org.khasanof.state.configurer.StateConfigurerReaderSingleton;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

/**
 * @author Nurislom
 * @see org.khasanof.util
 * @since 3/2/2024 10:47 AM
 */
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class PostgresqlRepositoryStrategyHelper {

    private final ApplicationEventPublisher eventPublisher;
    private final StateConfigurerReaderSingleton configReader;

    public PostgresqlRepositoryStrategyHelper(ApplicationEventPublisher eventPublisher, StateConfigurerReaderSingleton configReader) {
        this.eventPublisher = eventPublisher;
        this.configReader = configReader;
    }

    /**
     *
     * @param fltState
     * @return
     */
    public State createState(FltStateEntity fltState) {
        return new FltState(getState(fltState), fltState.getUser().getId(), fltState.getId(), eventPublisher);
    }

    @NotNull
    private Enum getState(FltStateEntity fltState) {
        return Enum.valueOf(configReader.getConfigReader().getStateType(), fltState.getState());
    }

    /**
     *
     * @param user
     * @return
     */
    public FltUserEntity userToFltUserEntity(User user) {
        FltUserEntity fltUserEntity = new FltUserEntity();
        fltUserEntity.setLastname(user.getLastName());
        fltUserEntity.setUsername(user.getUserName());
        fltUserEntity.setFirstname(user.getFirstName());
        fltUserEntity.setUserId((user.getId()));
        fltUserEntity.setLanguageCode(user.getLanguageCode());
        fltUserEntity.setSupportInlineQueries(user.getSupportInlineQueries());
        return fltUserEntity;
    }
}
