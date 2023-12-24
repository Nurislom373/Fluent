package org.khasanof.event.methodContext;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.khasanof.enums.HandleClasses;
import org.springframework.context.ApplicationEvent;

import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.event.methodContext
 * @since 8/9/2023 10:22 AM
 */
@Getter
@Setter
@ToString
public class MethodCollectedEvent extends ApplicationEvent {

    private Map<HandleClasses, Integer> longMap;

    public MethodCollectedEvent(Object source, Map<HandleClasses, Integer> longMap) {
        super(source);
        this.longMap = longMap;
    }

}
