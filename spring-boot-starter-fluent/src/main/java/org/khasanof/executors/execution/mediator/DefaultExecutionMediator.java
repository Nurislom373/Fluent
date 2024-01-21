package org.khasanof.executors.execution.mediator;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.enums.additional.AdditionalParamType;
import org.khasanof.event.ExecutionMethod;
import org.khasanof.executors.execution.Execution;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.executors.execution
 * @since 8/13/2023 9:19 PM
 */
@Slf4j
@Component
public class DefaultExecutionMediator implements ExecutionMediator, InitializingBean {

    private final ApplicationContext applicationContext;
    private final Map<AdditionalParamType, Execution> executions = new HashMap<>();

    public DefaultExecutionMediator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void execution(ExecutionMethod event) {
        try {
            tryExecution(event);
        } catch (InvocationTargetException | IllegalAccessException e) {
            log.warn("exception throwing. method name : {}, exception cause : {}",
                    event.getInvokerModel().getInvokerReference().getMethod().getName(), e.getCause().getMessage());
        }
    }

    @Override
    public void setExecutions(Map<AdditionalParamType, Execution> executions) {
        this.executions.putAll(executions);
    }

    private void tryExecution(ExecutionMethod event) throws InvocationTargetException, IllegalAccessException {
        Execution execution = getExecution(event);
        execution.run(event);
    }

    private Execution getExecution(ExecutionMethod event) {
        if (Objects.isNull(event.getInvokerModel().getAdditionalParam())) {
            return executions.get(null);
        }
        return executions.get(getAdditionalParamType(event));
    }

    private AdditionalParamType getAdditionalParamType(ExecutionMethod event) {
        return event.getInvokerModel()
                .getAdditionalParam()
                .getType();
    }

    @Override
    public void afterPropertiesSet() {
        applicationContext.getBeansOfType(Execution.class)
                .forEach((beanName, bean) -> this.executions.put(bean.getType(), bean));
    }
}
