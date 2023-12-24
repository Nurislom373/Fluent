package org.khasanof.executors.appropriate;

import org.khasanof.enums.HandleType;
import org.khasanof.models.executors.AppropriateMethod;
import org.khasanof.models.executors.AppropriateType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Nurislom
 * @see org.khasanof.executors.appropriate
 * @since 12/25/2023 12:31 AM
 */
@Service
@SuppressWarnings({"rawtypes", "unchecked"})
public class DefaultAppropriateMethodService implements AppropriateMethodService, InitializingBean {

    private final ApplicationContext applicationContext;
    private final Map<HandleType, List<AppropriateUpdateMethod>> appropriateMethods = new ConcurrentHashMap<>();

    public DefaultAppropriateMethodService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Optional<AppropriateMethod> getAppropriateMethod(AppropriateType appropriateType) {
        return appropriateMethods.getOrDefault((appropriateType.getType()).asHandleType(), Collections.emptyList())
                .stream().filter(appropriateUpdateMethod -> {
                    return appropriateUpdateMethod.isMatch(appropriateType.getValue());
                }).map(appropriateUpdateMethod -> {
                    return appropriateUpdateMethod.getAppropriate(appropriateType.getValue());
                }).findFirst();
    }

    @Override
    public void afterPropertiesSet() {
        applicationContext.getBeansOfType(AppropriateUpdateMethod.class)
                .forEach((beanName, bean) -> addAppropriateMethods(bean));
    }

    private void addAppropriateMethods(AppropriateUpdateMethod bean) {
        if (appropriateMethods.containsKey(bean.handleType())) {
            addExistHandleType(bean);
        } else {
            addNewHandleType(bean);
        }
    }

    private void addExistHandleType(AppropriateUpdateMethod bean) {
        List<AppropriateUpdateMethod> methodList = appropriateMethods.get(bean.handleType());
        methodList.add(bean);
    }

    private void addNewHandleType(AppropriateUpdateMethod bean) {
        appropriateMethods.put(bean.handleType(), new ArrayList<>() {{
            add(bean);
        }});
    }

}
