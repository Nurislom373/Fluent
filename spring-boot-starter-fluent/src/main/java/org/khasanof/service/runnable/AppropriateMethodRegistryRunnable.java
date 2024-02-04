package org.khasanof.service.runnable;

import org.khasanof.executors.appropriate.message.*;
import org.khasanof.registry.appropriate.AppropriateMethodRegistryContainer;
import org.springframework.stereotype.Service;

/**
 * @author Nurislom
 * @see org.khasanof.service.runnable
 * @since 2/4/2024 8:54 PM
 */
@Service
public class AppropriateMethodRegistryRunnable implements PostProcessor {

    private final AppropriateMethodRegistryContainer appropriateMethodRegistryContainer;

    public AppropriateMethodRegistryRunnable(AppropriateMethodRegistryContainer appropriateMethodRegistryContainer) {
        this.appropriateMethodRegistryContainer = appropriateMethodRegistryContainer;
    }

    @Override
    public void run() {
        appropriateMethodRegistryContainer.addAppropriateMethod(new AudioMessageAppropriateMethod());
        appropriateMethodRegistryContainer.addAppropriateMethod(new AnimationMessageAppropriateMethod());
        appropriateMethodRegistryContainer.addAppropriateMethod(new DocumentMessageAppropriateMethod());
        appropriateMethodRegistryContainer.addAppropriateMethod(new PhotoMessageAppropriateMethod());
        appropriateMethodRegistryContainer.addAppropriateMethod(new TextMessageAppropriateMethod());
        appropriateMethodRegistryContainer.addAppropriateMethod(new VideoMessageAppropriateMethod());
        appropriateMethodRegistryContainer.addAppropriateMethod(new VideoNoteMessageAppropriateMethod());
        appropriateMethodRegistryContainer.addAppropriateMethod(new WebAppDataAppropriateMethod());
    }
}
