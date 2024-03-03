package org.khasanof.inline.type;

import org.khasanof.feature.method.MethodType;

/**
 * @author Nurislom
 * @see org.khasanof.inline
 * @since 3/3/2024 5:35 PM
 */
public enum InlineMethodType implements MethodType {

    INLINE_QUERY {
        @Override
        public String getName() {
            return INLINE_QUERY.name();
        }
    }
}
