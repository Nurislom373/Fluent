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
public class HandlerMethod {

    private Method method;
    private List<Condition> conditions;
    private Object reference;

}
