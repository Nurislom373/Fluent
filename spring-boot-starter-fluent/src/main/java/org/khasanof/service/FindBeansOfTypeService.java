package org.khasanof.service;

import java.util.List;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.config
 * @since 1/27/2024 9:13 PM
 */
public interface FindBeansOfTypeService {

    <T> List<T> findAllByList(Class<T> clazz);

    <T> Map<String, T> findAllByMap(Class<T> clazz);

}
