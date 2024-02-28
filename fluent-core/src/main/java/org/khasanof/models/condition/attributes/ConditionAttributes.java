package org.khasanof.models.condition.attributes;

import org.khasanof.custom.attributes.Attributes;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author Nurislom
 * @see org.khasanof.models.condition
 * @since 2/26/2024 10:55 PM
 */
public interface ConditionAttributes extends Attributes {

    ConfigurableListableBeanFactory getBeanFactory();

}
