package org.khasanof.factories.appropriate;

import org.khasanof.enums.HandleType;
import org.khasanof.models.executors.AppropriateMethod;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof.factories.appropriate
 * @since 12/26/2023 1:20 AM
 */
@Component
public class DefaultAppropriateMethodFactory implements AppropriateMethodFactory {

    @Override
    public AppropriateMethod create(HandleType handleType, Object value) {
        return new AppropriateMethod(handleType, value);
    }

}
