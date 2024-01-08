package org.khasanof.memento;

import lombok.*;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.memento
 * @since 1/8/2024 10:19 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MethodInvokeMemento {

    private Method method;
    private Object[] args;
    private MethodProxy methodProxy;

}
