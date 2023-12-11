package org.khasanof.collector.questMethod;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.collector.GenericMethodContext;
import org.khasanof.context.FluentContextHolder;
import org.khasanof.enums.HandleClasses;
import org.khasanof.executors.matcher.CompositeMatcher;
import org.khasanof.model.invoker.SimpleInvoker;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.collector.questMethod
 * @since 12/9/2023 8:11 PM
 */
@Slf4j
//@Component
public class SyncSearchMethod implements DefaultSearchMethod {

    private final GenericMethodContext<HandleClasses, Map<Method, Object>> methodContext;
    private final CompositeMatcher matcher;

    public SyncSearchMethod(GenericMethodContext<HandleClasses, Map<Method, Object>> methodContext, CompositeMatcher matcher) {
        this.methodContext = methodContext;
        this.matcher = matcher;
    }

    @Override
    public boolean contains(HandleClasses handleClasses) {
        return methodContext.containsKey(handleClasses);
    }

    @Override
    public SimpleInvoker getMethodValueAnn(Object value, HandleClasses type) {
        log.info("contextHolder.getCurrentUpdate() = " + FluentContextHolder.getCurrentUpdate());
        System.out.printf("Enter type - %s, value - %s \n", type, value);
        if (type.isHasSubType()) {
            if (contains(type)) {

            }
        } else {

        }
        return null;
    }
}
