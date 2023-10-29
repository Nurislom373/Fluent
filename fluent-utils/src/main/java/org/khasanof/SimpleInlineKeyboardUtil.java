package org.khasanof;

import org.khasanof.flyweight.InlineKeyboardBuildStrategyFlyWeight;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 10/29/2023 11:42 PM
 */
public class SimpleInlineKeyboardUtil extends AbstractInlineKeyboardUtil {

    public SimpleInlineKeyboardUtil() {
        super(new InlineKeyboardBuildStrategyFlyWeight());
    }

}
