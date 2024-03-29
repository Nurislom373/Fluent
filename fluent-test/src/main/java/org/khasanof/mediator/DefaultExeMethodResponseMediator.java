package org.khasanof.mediator;

import org.khasanof.factories.response.ExecMethodResponse;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static org.khasanof.utils.FluentTestUtils.equalsMethods;

/**
 * @author Nurislom
 * @see org.khasanof.factories.response
 * @since 1/14/2024 12:02 AM
 */
public class DefaultExeMethodResponseMediator implements ExecMethodResponseMediator {

    private final List<ExecMethodResponse> responses = new ArrayList<>();

    @Override
    public Object createResponse(Method method, Object[] args) {
        return responses.stream()
                .filter(execMethodResponse -> Objects.equals(method.getReturnType(), execMethodResponse.returnType()))
                .map(execMethodResponse -> tryCreateResponse(method, args, execMethodResponse))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Match method not found!"));
    }

    private Object tryCreateResponse(Method method, Object[] args, ExecMethodResponse execMethodResponse) {
        try {
            return execMethodResponse.createResponse(method, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setExecMethodResponseMap(Collection<ExecMethodResponse> responses) {
        if (Objects.nonNull(responses) && !responses.isEmpty()) {
            this.responses.addAll(responses);
        }
    }
}
