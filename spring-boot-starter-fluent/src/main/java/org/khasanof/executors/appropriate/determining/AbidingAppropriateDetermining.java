package org.khasanof.executors.appropriate.determining;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.executors.appropriate.AppropriateMethodService;
import org.khasanof.executors.appropriate.AppropriateTypeService;
import org.khasanof.factories.appropriate.AppropriateMethodFactory;
import org.khasanof.models.executors.AppropriateMethod;
import org.khasanof.models.executors.AppropriateType;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.executors
 * @since 12/24/2023 4:53 PM
 */
@Slf4j
@Service
public final class AbidingAppropriateDetermining implements AppropriateDetermining {

    private final AppropriateMethodFactory appropriateMethodFactory;
    private final AppropriateMethodService appropriateMethodService;
    private final AppropriateTypeService appropriateTypeService;

    public AbidingAppropriateDetermining(AppropriateMethodFactory appropriateMethodFactory,
                                         AppropriateMethodService appropriateMethodService,
                                         AppropriateTypeService appropriateTypeService) {

        this.appropriateMethodFactory = appropriateMethodFactory;
        this.appropriateMethodService = appropriateMethodService;
        this.appropriateTypeService = appropriateTypeService;
    }

    @Override
    public Optional<AppropriateMethod> determining(Update update) {
        return appropriateTypeService.getAppropriateType(update)
                .flatMap(this::internalDetermining);
    }

    private Optional<AppropriateMethod> internalDetermining(AppropriateType appropriateType) {
        if (appropriateType.isHasSubMethods()) {
            return appropriateMethodService.getAppropriateMethod(appropriateType);
        }
        return Optional.of(createAppropriateMethod(appropriateType));
    }

    private AppropriateMethod createAppropriateMethod(AppropriateType appropriateType) {
        return appropriateMethodFactory.create(appropriateType.getType().asHandleType(), appropriateType.getValue());
    }

}
