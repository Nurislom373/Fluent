package org.khasanof.event.assembleMethods;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

/**
 * @author Nurislom
 * @see org.khasanof.event
 * @since 8/19/2023 4:53 PM
 */
@Getter
@Setter
@ToString
public class AssembleMethodsEvent extends ApplicationEvent {

    public AssembleMethodsEvent(Object source) {
        super(source);
    }

}
