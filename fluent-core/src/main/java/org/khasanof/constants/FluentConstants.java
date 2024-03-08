package org.khasanof.constants;

import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 12/9/2023 2:35 PM
 */
public abstract class FluentConstants {

    public static final String BASE_PACKAGE = "org.khasanof";

    // Orders
    public static final int HIGH_ORDER = 1;
    public static final int DEFAULT_ORDER = 5;
    public static final int LOW_ORDER = 10;

    public static final Set<String> IGNORE_METHOD_NAMES = Set.of("toString", "equals", "hashCode");

    public static final String INVOKER_TYPE = "invoker_type";
    public static final String ADDITIONAL_METHOD_PARAM = "additional_method_param";

}
