package org.khasanof.collector.questMethod;

import org.khasanof.collector.GenericMethodContext;
import org.khasanof.converter.HandleTypeConverter;
import org.khasanof.enums.HandleClasses;
import org.khasanof.executors.matcher.CompositeMatcher;
import org.khasanof.factories.invoker.method.InvokerMethodFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.collector.questMethod
 * @since 12/17/2023 4:38 PM
 */
@Configuration
public class SearchMethodConfiguration {

    @Autowired
    private GenericMethodContext<HandleClasses, Map<Method, Object>> methodContext;

    @Bean
    BaseSearchMethod searchMethod(InvokerMethodFactory invokerMethodFactory,
                                  HandleTypeConverter handleTypeConverter,
                                  CompositeMatcher matcher) {
        return new DefaultSearchMethod(handleTypeConverter, invokerMethodFactory, methodContext, matcher);
    }

}
