package org.khasanof.context;

import lombok.Data;
import org.khasanof.custom.attributes.Attributes;
import org.khasanof.custom.attributes.DefaultAttributes;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Nurislom
 * @see org.khasanof.context
 * @since 10/28/2023 6:53 PM
 */
public class SimpleFluentUpdateContext implements FluentUpdateContext {

    private final Map<String, FluentUpdate> updateMap = new ConcurrentHashMap<>();

    @Override
    public void addContext(String threadName, Update update) {
        this.updateMap.put(threadName, fluentUpdateBuilder(update));
    }

    @Override
    public void removeContext(String threadName) {
        this.updateMap.remove(threadName);
    }

    @Override
    public Optional<FluentUpdate> getFluentUpdate(String threadName) {
        return Optional.ofNullable(this.updateMap.get(threadName));
    }

    private FluentUpdate fluentUpdateBuilder(Update update) {
        return new PrivateFluentUpdate(update, new DefaultAttributes());
    }

    @Data
    protected static class PrivateFluentUpdate implements FluentUpdate {

        private Update update;
        private Attributes attributes;

        public PrivateFluentUpdate(Update update, Attributes attributes) {
            this.update = update;
            this.attributes = attributes;
        }
    }
}
