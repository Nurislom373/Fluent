package org.khasanof.verifier.assertions;

import org.khasanof.memento.DefaultMethodInvokeHistory;
import org.khasanof.memento.MethodInvokeHistory;
import org.khasanof.method.DefaultExecuteMethodReflect;
import org.khasanof.method.ExecuteMethodReflect;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/8/2024 11:21 PM
 */
public class DefaultVerifierAssertionsBuilder {

    private MethodInvokeHistory methodInvokeHistory;
    private ExecuteMethodReflect executeMethodReflect;

    public DefaultVerifierAssertionsBuilder() {
        this.methodInvokeHistory = new DefaultMethodInvokeHistory();
        this.executeMethodReflect = new DefaultExecuteMethodReflect();
    }

    public DefaultVerifierAssertionsBuilder methodInvokerHistory(MethodInvokeHistory methodInvokeHistory) {
        this.methodInvokeHistory = methodInvokeHistory;
        return this;
    }

    public DefaultVerifierAssertionsBuilder executeMethodReflect(ExecuteMethodReflect executeMethodReflect) {
        this.executeMethodReflect = executeMethodReflect;
        return this;
    }

    public VerifierAssertions build() {
        return new DefaultVerifierAssertions(methodInvokeHistory, executeMethodReflect);
    }

}
