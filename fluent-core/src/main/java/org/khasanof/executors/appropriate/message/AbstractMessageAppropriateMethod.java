package org.khasanof.executors.appropriate.message;

import org.khasanof.executors.appropriate.AppropriateUpdateMethod;
import org.khasanof.models.executors.AppropriateMethod;
import org.khasanof.models.executors.UpdateType;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.function.BooleanSupplier;

/**
 * @author Nurislom
 * @see org.khasanof.executors.appropriate.message
 * @since 12/24/2023 8:39 PM
 */
public abstract class AbstractMessageAppropriateMethod extends AppropriateUpdateMethod<Message> {

    protected boolean tryIsMatch(BooleanSupplier supplier) {
        return Objects.equals(supplier.getAsBoolean(), Boolean.TRUE);
    }

    protected AppropriateMethod createAppropriateMethod(Class<? extends Annotation> annotation, Object value) {
        return new AppropriateMethod(annotation, value);
    }

    @Override
    public UpdateType handleType() {
        return UpdateType.MESSAGE;
    }
}
