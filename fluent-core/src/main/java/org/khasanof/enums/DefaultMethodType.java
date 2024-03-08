package org.khasanof.enums;

import org.khasanof.feature.method.MethodType;

/**
 * @author Nurislom
 * @see org.khasanof.enums
 * @since 1/27/2024 8:38 PM
 */
public enum DefaultMethodType implements MethodType {

    DEFAULT {
        @Override
        public String getName() {
            return DEFAULT.name();
        }
    },
    STATE {
        @Override
        public String getName() {
            return STATE.name();
        }
    },
    HANDLE_ANY {
        @Override
        public String getName() {
            return HANDLE_ANY.name();
        }
    },
    EXCEPTION_HANDLER {
        @Override
        public String getName() {
            return EXCEPTION_HANDLER.name();
        }
    },
    VAR_EXPRESSION {
        @Override
        public String getName() {
            return VAR_EXPRESSION.name();
        }
    },
    PROCESS_FILE {
        @Override
        public String getName() {
            return PROCESS_FILE.name();
        }
    }
}
