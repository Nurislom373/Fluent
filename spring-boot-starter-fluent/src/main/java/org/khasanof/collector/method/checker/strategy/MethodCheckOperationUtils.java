package org.khasanof.collector.method.checker.strategy;

import java.util.Arrays;
import java.util.function.BooleanSupplier;

/**
 * @author Nurislom
 * @see org.khasanof.collector.method.checker.strategy
 * @since 1/21/2024 2:53 PM
 */
public abstract class MethodCheckOperationUtils {

    public static boolean paramsTypeCheck(Class<?>[] methodParams, Class<?>[] matchParams) {
        return Arrays.stream(matchParams)
                .allMatch(param -> Arrays.asList(methodParams).contains(param));
    }

    public static void checkFalseThen(boolean result, RuntimeException exception) {
        if (!result) throw exception;
    }

    public static void checkFalseThen(BooleanSupplier supplier, RuntimeException exception) {
        if (!supplier.getAsBoolean()) throw exception;
    }

    public static int countWordsInBraces(String input) {
        int count = 0;
        boolean insideBraces = false;

        for (char currentChar : input.toCharArray()) {
            if (currentChar == '{') insideBraces = true;
            else if (currentChar == '}') count++;
            else if (Character.isWhitespace(currentChar) && insideBraces) count++;
        }

        return count;
    }

}
