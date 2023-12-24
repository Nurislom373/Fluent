package org.khasanof.collector;

import org.khasanof.models.invoker.SimpleInvoker;

import java.util.Optional;

/**
 * Author: Nurislom
 * <br/>
 * Date: 18.06.2023
 * <br/>
 * Time: 12:29
 * <br/>
 * Package: org.khasanof.springbootstarterfluent.core.collector
 */
public interface Collector<P> extends HandleTypeCollector {

    Optional<SimpleInvoker> getInvokerResult(Object value, P param);

    boolean hasHandle(P param);

}
