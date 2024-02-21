package org.khasanof.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.config
 * @since 1/27/2024 9:13 PM
 */
public interface FindBeansOfTypeService {

    /**
     *
     * @param clazz
     * @return
     * @param <T>
     */
    <T> List<T> findAllByList(Class<T> clazz);

    /**
     *
     * @param clazz
     * @return
     * @param <T>
     */
    <T> Map<String, T> findAllByMap(Class<T> clazz);

    /**
     *
     * @param beanClass
     * @return
     * @param <T>
     */
    <T> Optional<T> findBean(Class<T> beanClass);
}
