package org.khasanof.service;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.service
 * @since 1/27/2024 9:13 PM
 */
@Service
public class DefaultFindBeansOfTypeService implements FindBeansOfTypeService {

    private final ApplicationContext applicationContext;

    public DefaultFindBeansOfTypeService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public <T> List<T> findAllByList(Class<T> clazz) {
        return new ArrayList<>(applicationContext.getBeansOfType(clazz).values());
    }

    @Override
    public <T> Map<String, T> findAllByMap(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz);
    }
}
