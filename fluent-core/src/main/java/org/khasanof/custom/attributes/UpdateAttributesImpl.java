package org.khasanof.custom.attributes;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Nurislom
 * @see org.khasanof.custom.scope.fluent
 * @since 12/10/2023 8:13 PM
 */
public class UpdateAttributesImpl implements UpdateAttributes {

    private final Map<String, Object> attributes = new ConcurrentHashMap<>();

    @Override
    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    @Override
    public Object getAttribute(String name) {
        return attributes.getOrDefault(name, null);
    }

    @Override
    public Object removeAttribute(String name) {
        Object object = attributes.get(name);
        if (object != null) {
            attributes.remove(name);
        }
        return object;
    }

    @Override
    public Set<String> getAttributeNames() {
        return attributes.keySet();
    }
}
