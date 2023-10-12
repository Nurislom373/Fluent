package org.khasanof.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.khasanof.model.InvokerModelV2;
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
public class MethodV1Event extends ApplicationEvent {

    private InvokerModelV2 invokerModel;
    private Map.Entry<Method, Object> classEntry;
    private Method method;

    public MethodV1Event(Object source, InvokerModelV2 invokerModel, Map.Entry<Method, Object> classEntry, Method method) {
        super(source);
        this.invokerModel = invokerModel;
        this.classEntry = classEntry;
        this.method = method;
    }
}
