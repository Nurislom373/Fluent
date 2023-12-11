package org.khasanof.executors.execution;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.enums.additional.AdditionalParamType;
import org.khasanof.event.MethodV1Event;
import org.khasanof.model.AdditionalType;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
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

    public void execution(MethodV1Event event) {
        tryExecution(event);
    }

    private void tryExecution(MethodV1Event event) {
        try {
            if (Objects.isNull(event.getInvokerModel().getAdditionalParam())) {
                simpleExecution.run(event);
            } else {
                executions.get(getAdditionalParamType(event)).run(event);
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            log.warn("exception throwing. method name : {}, exception cause : {}",
                    event.getMethod().getName(), e.getCause().getMessage());
        }
    }

    private AdditionalParamType getAdditionalParamType(MethodV1Event event) {
        return event.getInvokerModel().getAdditionalParam().getType();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext.getBeansOfType(Execution.class)
                .forEach((beanName, bean) -> {
                    if (Objects.nonNull(bean.getType())) {
                        executions.put(bean.getType(), bean);
                    } else {
                        simpleExecution = bean;
                    }
                });
    }
}
