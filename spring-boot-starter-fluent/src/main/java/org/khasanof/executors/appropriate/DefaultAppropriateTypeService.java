package org.khasanof.executors.appropriate;

import org.khasanof.models.executors.AppropriateType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Nurislom
 * @see org.khasanof.executors.appropriate
 * @since 12/25/2023 12:23 AM
 */
@Service
public class DefaultAppropriateTypeService implements AppropriateTypeService, InitializingBean {

    private final ApplicationContext applicationContext;
    private final List<AppropriateUpdateType> updateTypeMatchers = new CopyOnWriteArrayList<>();

    public DefaultAppropriateTypeService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Optional<AppropriateType> getAppropriateType(Update update) {
        for (AppropriateUpdateType typeMatcher : updateTypeMatchers) {
            if (typeMatcher.isMatch(update)) {
                return Optional.of(typeMatcher.getAppropriate(update));
            }
        }
        return Optional.empty();
    }

    @Override
    public void afterPropertiesSet() {
        applicationContext.getBeansOfType(AppropriateUpdateType.class)
                .forEach((beanName, bean) -> updateTypeMatchers.add(bean));
    }

}
