package org.khasanof.factories.response.decorators;

import org.khasanof.factories.response.ExecMethodResponse;
import org.khasanof.factories.response.ExecMethodResponseDecorator;

import java.lang.reflect.Method;
import java.util.concurrent.CompletableFuture;

/**
 * @author Nurislom
 * @see org.khasanof.factories.response.decorators
 * @since 1/13/2024 11:46 PM
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class ExecMethodResponseWrapFutureDecorator extends ExecMethodResponseDecorator {

    public ExecMethodResponseWrapFutureDecorator(ExecMethodResponse executeMethodResponse) {
        super(executeMethodResponse);
    }

    @Override
    public Object createResponse(Method method, Object[] args) {
        CompletableFuture completableFuture = new CompletableFuture();
        try {
            completableFuture.complete(super.createResponse(method, args));
        } catch (Exception e) {
            completableFuture.completeExceptionally(e);
        }
        return completableFuture;
    }
}
