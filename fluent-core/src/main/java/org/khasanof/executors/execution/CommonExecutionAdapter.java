package org.khasanof.executors.execution;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.enums.additional.AdditionalParamType;
import org.khasanof.event.ExecutionMethod;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Nurislom
 * @see org.khasanof.executors.execution
 * @since 8/13/2023 9:19 PM
 */
@Slf4j
@Component(CommonExecutionAdapter.NAME)
public class CommonExecutionAdapter implements ApplicationContextAware {

    public static final String NAME = "commonExecutionAdapter";

    private final Map<AdditionalParamType, Execution> executions = new ConcurrentHashMap<>();
    private Execution simpleExecution;

    public void execution(ExecutionMethod event) {
        try {
            tryExecution(event);
        } catch (InvocationTargetException | IllegalAccessException e) {
            log.warn("exception throwing. method name : {}, exception cause : {}",
                    event.getInvokerModel().getInvokerReference().getMethod().getName(), e.getCause().getMessage());
        }
    }

    private void tryExecution(ExecutionMethod event) throws InvocationTargetException, IllegalAccessException {
        if (Objects.isNull(event.getInvokerModel().getAdditionalParam())) {
            simpleExecution.run(event);
        } else {
            executions.get(getAdditionalParamType(event)).run(event);
        }
    }

    private AdditionalParamType getAdditionalParamType(ExecutionMethod event) {
        return event.getInvokerModel()
                .getAdditionalParam()
                .getType();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext.getBeansOfType(Execution.class)
                .forEach((beanName, bean) -> addExecutions(bean));
    }

    private void addExecutions(Execution bean) {
        if (Objects.nonNull(bean.getType())) {
            executions.put(bean.getType(), bean);
        } else {
            simpleExecution = bean;
        }
    }
}
