package org.khasanof.state;

import java.util.Arrays;
import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.state
 * @since 8/15/2023 9:57 PM
 */
public interface DefaultNext<S> {

    @SuppressWarnings("unchecked")
    default S getNext(S state) {
        List<S> enums = (List<S>) Arrays.stream(state.getClass().getEnumConstants())
                .dropWhile(ste -> !ste.equals(state)).toList();
        if (enums.size() >= 2) {
            return enums.get(1);
        }
        return enums.get(0);
    }

}
