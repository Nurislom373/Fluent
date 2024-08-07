package org.khasanof.service;

import org.khasanof.entity.FltStateEntity;
import org.khasanof.repository.FltStateRepository;
import org.khasanof.state.event.FltStateEvent;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author Nurislom
 * @see org.khasanof.service
 * @since 3/2/2024 12:16 PM
 */
@Service
@Primary
public class PostgresqlFltStateEventService implements FltStateEventService {

    private final FltStateRepository fltStateRepository;

    public PostgresqlFltStateEventService(FltStateRepository fltStateRepository) {
        this.fltStateRepository = fltStateRepository;
    }

    @Override
    public void updateState(FltStateEvent event) {
        fltStateRepository.findById(event.getState().getStateId())
                .ifPresent(fltStateEntity -> updateStateInternal(event, fltStateEntity));
    }

    private void updateStateInternal(FltStateEvent event, FltStateEntity fltStateEntity) {
        fltStateEntity.setState(event.getState().getState().toString());
        fltStateRepository.save(fltStateEntity);
    }
}
