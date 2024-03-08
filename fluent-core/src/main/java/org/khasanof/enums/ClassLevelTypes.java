package org.khasanof.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.khasanof.annotation.ExceptionController;
import org.khasanof.annotation.StateController;
import org.khasanof.annotation.UpdateController;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Nurislom
 * @see org.khasanof.enums
 * @since 04.07.2023 22:04
 */
@Getter
@RequiredArgsConstructor
public enum ClassLevelTypes {

    EXCEPTION_CONTROLLER(ExceptionController.class),
    STATE_CONTROLLER(StateController.class),
    UPDATE_CONTROLLER(UpdateController.class);

    private final Class<? extends Annotation> type;

    public static Set<Class<? extends Annotation>> getAllAnnotations() {
        return Arrays.stream(values()).map(ClassLevelTypes::getType)
                .collect(Collectors.toSet());
    }
}
