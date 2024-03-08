package org.khasanof;

import java.util.Collection;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 2/1/2024 9:54 PM
 */
public interface GenericSetParam<P> {

    void set(P p);

    void setAll(Collection<P> collection);

}
