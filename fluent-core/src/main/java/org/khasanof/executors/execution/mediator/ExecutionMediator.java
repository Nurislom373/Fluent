package org.khasanof.executors.execution.mediator;

import org.khasanof.enums.additional.AdditionalParamType;
import org.khasanof.event.ExecutionMethod;
import org.khasanof.executors.execution.Execution;

import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.executors.execution.mediator
 * @since 1/21/2024 4:36 PM
 */
public interface ExecutionMediator {

    void execution(ExecutionMethod event);

    void setExecutions(Map<AdditionalParamType, Execution> executions);

}
