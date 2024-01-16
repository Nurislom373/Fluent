package org.khasanof.executors.invoker.param;

import org.khasanof.enums.additional.AdditionalParamType;
import org.khasanof.models.Invoker;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Take Additional Param With Type
 *
 * @author Nurislom
 * @see org.khasanof.executors.invoker
 * @since 8/13/2023 7:02 PM
 */
@Component(AdditionalParamServiceAdapter.NAME)
public class AdditionalParamServiceAdapter implements InitializingBean {

    public static final String NAME = "twtCommonAdapter";
    private final Map<AdditionalParamType, AdditionalParamService> twtMap = new HashMap<>();
    private final ApplicationContext applicationContext;

    public AdditionalParamServiceAdapter(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Object getParam(AdditionalParamType type, Invoker invokerModel, Object[] args, Method method) {
        return twtMap.get(type).getValue(invokerModel, args, method);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        applicationContext.getBeansOfType(AdditionalParamService.class).values()
                .forEach(twt -> twtMap.put(twt.getType(), twt));
    }

}
