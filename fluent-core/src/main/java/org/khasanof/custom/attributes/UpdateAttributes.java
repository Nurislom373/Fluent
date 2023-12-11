package org.khasanof.custom.attributes;

import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.custom.scope.fluent
 * @since 12/9/2023 2:41 PM
 */
public interface UpdateAttributes {

    void setAttribute(String name, Object value);

    Object getAttribute(String name);

    Object removeAttribute(String name);

    Set<String> getAttributeNames();

}
