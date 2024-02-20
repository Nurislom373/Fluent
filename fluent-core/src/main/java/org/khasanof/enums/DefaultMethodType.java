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
            return DEFAULT.getName();
        }
    },
    STATE {
        @Override
        public String getName() {
            return STATE.getName();
        }
    },
    HANDLE_ANY {
        @Override
        public String getName() {
            return HANDLE_ANY.getName();
        }
    },
    EXCEPTION_HANDLER {
        @Override
        public String getName() {
            return EXCEPTION_HANDLER.getName();
        }
    },
    VAR_EXPRESSION {
        @Override
        public String getName() {
            return VAR_EXPRESSION.getName();
        }
    },
    PROCESS_FILE {
        @Override
        public String getName() {
            return PROCESS_FILE.getName();
        }
    }
}
