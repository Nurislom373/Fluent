package org.khasanof.executors.appropriate.message;

import org.khasanof.enums.HandleType;
import org.khasanof.executors.appropriate.AppropriateUpdateMethod;
import org.khasanof.models.executors.AppropriateMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/**
 * @author Nurislom
 * @see org.khasanof.executors.appropriate.message
 * @since 12/24/2023 8:39 PM
 */
public abstract class AbstractMessageAppropriateMethod extends AppropriateUpdateMethod<Message> {

    protected boolean tryIsMatch(BooleanSupplier supplier) {
        return Objects.equals(supplier.getAsBoolean(), Boolean.TRUE);
    }

    protected AppropriateMethod tryGetAppropriate(Supplier<Object> supplier) {
        return new AppropriateMethod(handleType(), supplier.get());
    }

    @Override
    public HandleType handleType() {
        return HandleType.MESSAGE;
    }

}
