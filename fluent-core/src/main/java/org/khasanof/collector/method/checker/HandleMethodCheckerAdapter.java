package org.khasanof.collector.method.checker;

import org.khasanof.GenericCheck;

import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @author Nurislom
 * @see org.khasanof.collector.method.checker
 * @since 12/26/2023 9:29 PM
 */
public interface HandleMethodCheckerAdapter extends GenericCheck<Method> {

    void setMethodCheckers(Collection<HandleMethodChecker> methodCheckers);

}
