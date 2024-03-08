package org.khasanof.collector.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author Nurislom
 * @see org.khasanof.collector.context
 * @since 1/15/2024 11:19 PM
 */
@Slf4j
@Service
public class DefaultContextOperationExecutor implements ContextOperationExecutor {

    private final ApplicationContext applicationContext;

    public DefaultContextOperationExecutor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public <P, R> R execute(Class<? extends ContextOperation<P, R>> operationClass, P data) {
        return applicationContext.getBean(operationClass)
                .execute(data);
    }
}
