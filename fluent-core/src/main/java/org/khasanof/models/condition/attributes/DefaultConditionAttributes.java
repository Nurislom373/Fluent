package org.khasanof.models.condition.attributes;

import lombok.*;
import org.khasanof.custom.attributes.DefaultAttributes;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author Nurislom
 * @see org.khasanof.models.condition.attributes
 * @since 2/26/2024 10:56 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DefaultConditionAttributes extends DefaultAttributes implements ConditionAttributes {

    private ConfigurableListableBeanFactory beanFactory;

}
