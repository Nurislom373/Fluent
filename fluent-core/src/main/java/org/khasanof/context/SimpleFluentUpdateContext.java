package org.khasanof.context;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Nurislom
 * @see org.khasanof.context
 * @since 10/28/2023 6:53 PM
 */
@Component
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
        return new PrivateFluentUpdate(update);
    }

    @Data
    protected static class PrivateFluentUpdate implements FluentUpdate {

        private Update update;

        public PrivateFluentUpdate(Update update) {
            this.update = update;
        }
    }

}
