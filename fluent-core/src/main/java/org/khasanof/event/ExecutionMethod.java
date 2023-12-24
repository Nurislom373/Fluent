package org.khasanof.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.khasanof.models.Invoker;
import org.springframework.context.ApplicationEvent;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.event
 * @since 8/13/2023 8:59 PM
 */
@Getter
@Setter
@ToString
public class ExecutionMethod extends ApplicationEvent {

    private Invoker invokerModel;

    public ExecutionMethod(Object source, Invoker invokerModel) {
        super(source);
        this.invokerModel = invokerModel;
    }
}
