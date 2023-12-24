package org.khasanof.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.khasanof.models.condition.Condition;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.executors
 * @since 10/28/2023 12:07 AM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoredMethodType implements StoredType {

    private List<Condition> conditions;
    private Method method;
    private Object reference;

}
