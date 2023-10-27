package org.khasanof.query;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.query
 * @since 10/27/2023 11:05 PM
 */
@Component
public class SimpleQueryRepository implements QueryRepository, InitializingBean {

    private final Map<QueriesEnum, String> queries = new HashMap<>();

    @Override
    public String get(QueriesEnum query) {
        return this.queries.get(query);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        queries.put(QueriesEnum.SELECT_ALL, "select * from :table");
        queries.put(QueriesEnum.COUNT, "select count(*) from :table");
        queries.put(QueriesEnum.SELECT_ONE, "select * from :table where user_id = :user_id");
    }
}
