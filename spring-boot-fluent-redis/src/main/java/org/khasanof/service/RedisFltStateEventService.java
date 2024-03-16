package org.khasanof.service;

import org.khasanof.entity.FltStateHash;
import org.khasanof.repository.FltStateRepository;
import org.khasanof.state.event.FltStateEvent;
import org.springframework.stereotype.Service;

/**
 * @author Nurislom
 * @see org.khasanof.service
 * @since 3/17/2024 12:27 AM
 */
@Service
public class RedisFltStateEventService implements FltStateEventService {

    private final FltStateRepository fltStateRepository;

    public RedisFltStateEventService(FltStateRepository fltStateRepository) {
        this.fltStateRepository = fltStateRepository;
    }

    @Override
    public void updateState(FltStateEvent event) {
        fltStateRepository.findById(event.getState().getStateId())
                .ifPresent(fltStateHash -> updateStateInternal(event, fltStateHash));
    }

    private void updateStateInternal(FltStateEvent event, FltStateHash fltStateHash) {
        fltStateHash.setState(event.getState().getState().toString());
        fltStateRepository.save(fltStateHash);
    }
}
