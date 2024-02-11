package org.khasanof.custom;

import org.khasanof.condition.Condition;
import org.khasanof.config.DefineProcessType;
import org.khasanof.enums.ProcessType;

/**
 * @author Nurislom
 * @see org.khasanof.custom
 * @since 9/16/2023 10:53 PM
 */
public abstract class ProcessTypeResolver {

    public static boolean hasAcceptProcessType(DefineProcessType processingType, ProcessType processType) {
        return Condition.isTrue(processType.equals(ProcessType.BOTH))
                .thenGet(() -> true)
                .elseIf(processingType.processType().equals(ProcessType.BOTH))
                        .thenGet(() -> true)
                        .elseIf(processingType.processType().equals(processType))
                                    .thenGet(() -> true)
                .elseValue(false);
    }
}
