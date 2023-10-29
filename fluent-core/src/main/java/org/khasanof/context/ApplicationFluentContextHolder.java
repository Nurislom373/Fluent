package org.khasanof.context;

import org.springframework.stereotype.Service;

/**
 * @author Nurislom
 * @see org.khasanof.context
 * @since 10/28/2023 7:24 PM
 */
@Service
public class ApplicationFluentContextHolder implements FluentContextHolder {

    private final FluentUpdateContext updateContext;

    public ApplicationFluentContextHolder(FluentUpdateContext updateContext) {
        this.updateContext = updateContext;
    }

    @Override
    public FluentUpdate getCurrentUpdate() {
        var ref = new Object() {
            FluentUpdate update;
        };
        updateContext.getFluentUpdate(Thread.currentThread().getName())
                .ifPresentOrElse(fluentUpdate -> ref.update = fluentUpdate, () -> {
                    throw new RuntimeException("Update context not found!");
                });
        return ref.update;
    }

}
