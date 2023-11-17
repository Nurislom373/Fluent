package org.khasanof.parameter;

import org.khasanof.query.QueriesEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 10/27/2023 11:34 PM
 */
@Component
public class SqlParameterSourceAdapter implements InitializingBean {

    private final ApplicationContext applicationContext;
    private final Map<QueriesEnum, SqlParameterStrategy> strategyMap = new HashMap<>();

    public SqlParameterSourceAdapter(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public SqlParameterSource create(QueriesEnum query, Object... args) {
        return this.strategyMap.get(query).create(args);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        applicationContext.getBeansOfType(SqlParameterStrategy.class)
                .forEach((beanName, bean) -> strategyMap.put(bean.queryType(), bean));
    }
}
