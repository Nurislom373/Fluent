package org.khasanof.mediator;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.enums.DefaultMethodType;
import org.khasanof.executors.execution.Perform;
import org.khasanof.feature.method.MethodType;
import org.khasanof.models.invoker.InvokerParam;
import org.khasanof.models.invoker.SimpleInvoker;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.mediator
 * @since 2/1/2024 11:41 PM
 */
@Slf4j
@Component
public class DefaultPerformMediator implements PerformMediator, InitializingBean {

    private final ApplicationContext applicationContext;
    private final Map<MethodType, Perform> methodTypePerformMap = new HashMap<>();

    public DefaultPerformMediator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void execute(SimpleInvoker simpleInvoker) throws InvocationTargetException, IllegalAccessException {
        Perform perform = methodTypePerformMap.get(getType(simpleInvoker));
        if (Objects.isNull(perform)) {
            log.debug("perform not found : {}", getType(simpleInvoker));
        }
        perform.execute(simpleInvoker);
    }

    private MethodType getType(SimpleInvoker simpleInvoker) {
        MethodType defaultMethodType = getMethodType(simpleInvoker);

        if (Objects.equals(defaultMethodType, DefaultMethodType.HANDLE_ANY)) {
            defaultMethodType = DefaultMethodType.DEFAULT;
        }
        return defaultMethodType;
    }

    private MethodType getMethodType(SimpleInvoker simpleInvoker) {
        return (MethodType) simpleInvoker.getParams().get(InvokerParam.METHOD_TYPE);
    }

    @Override
    public void afterPropertiesSet() {
        applicationContext.getBeansOfType(Perform.class)
                .forEach((beanName, bean) -> methodTypePerformMap.put(bean.getType(), bean));
    }
}
