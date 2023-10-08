package org.khasanof.utils;

import org.khasanof.enums.MemoryUnits;

/**
 * @author Nurislom
 * @see org.khasanof.utils
 * @since 07.07.2023 23:32
 */
public abstract class MemoryUtils {

    public static String getFormula(MemoryUnits units) {
        return switch (units) {
            case KB -> "value / (10 ^ 3)";
            case MB -> "value / (10 ^ 6)";
            case GB -> "value / (10 ^ 9)";
        };
    }

}
