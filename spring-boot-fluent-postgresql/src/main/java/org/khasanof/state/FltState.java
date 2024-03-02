package org.khasanof.state;

import lombok.*;
import org.khasanof.state.event.FltStateEvent;
import org.springframework.context.ApplicationEventPublisher;

/**
 * @author Nurislom
 * @see org.khasanof.state
 * @since 3/2/2024 1:05 AM
 */
@Getter
@Setter
@ToString
public class FltState extends DefaultState {

    private Long userId;
    private Long stateId;
    private ApplicationEventPublisher publisher;

    public FltState(Enum state, Long userId, Long stateId, ApplicationEventPublisher publisher) {
        super(state);
        this.userId = userId;
        this.stateId = stateId;
        this.publisher = publisher;
    }

    public FltState(Enum state, Long stateId, Long userId) {
        super(state);
        this.stateId = stateId;
        this.userId = userId;
    }

    @Override
    public void nextState() {
        super.nextState();
        publisher.publishEvent(new FltStateEvent(this));
    }

    @Override
    public void nextState(Enum state) {
        super.nextState(state);
        publisher.publishEvent(new FltStateEvent(this));
    }
}
