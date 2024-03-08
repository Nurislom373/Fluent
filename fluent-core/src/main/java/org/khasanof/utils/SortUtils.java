package org.khasanof.utils;

import org.khasanof.SortOrder;

import java.util.Comparator;
import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.utils
 * @since 2/1/2024 9:59 PM
 */
public abstract class SortUtils {

    public static void sortList(List<? extends SortOrder> processors) {
        processors.sort(Comparator.comparing(SortOrder::getOrder));
    }
}
