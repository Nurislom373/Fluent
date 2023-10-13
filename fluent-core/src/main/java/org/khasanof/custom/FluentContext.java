package org.khasanof.custom;

/**
 * @author Nurislom
 * @see org.khasanof.custom
 * @since 05.07.2023 0:14
 */
public class FluentContext {

    public static ThreadLocal<Boolean> updateExecutorBoolean = ThreadLocal.withInitial(() -> false);

    public static ThreadLocal<Boolean> determinationServiceBoolean = ThreadLocal.withInitial(() -> false);

}
