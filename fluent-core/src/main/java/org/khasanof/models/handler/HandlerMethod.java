package org.khasanof.models.handler;

import lombok.*;
import org.khasanof.models.condition.Condition;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.models.handler
 * @since 10/19/2023 7:58 PM
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HandlerMethod implements Handler {

    private Method method;
    private Object reference;
    private List<Condition> conditions;

}
