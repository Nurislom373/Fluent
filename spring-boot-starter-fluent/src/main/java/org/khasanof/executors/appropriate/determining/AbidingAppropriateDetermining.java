package org.khasanof.executors.appropriate.determining;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.executors.appropriate.AppropriateMethodService;
import org.khasanof.executors.appropriate.AppropriateTypeService;
import org.khasanof.executors.appropriate.determining.AppropriateDetermining;
import org.khasanof.models.executors.AppropriateMethod;
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

    private final AppropriateMethodService appropriateMethodService;
    private final AppropriateTypeService appropriateTypeService;

    public AbidingAppropriateDetermining(AppropriateMethodService appropriateMethodService, AppropriateTypeService appropriateTypeService) {
        this.appropriateMethodService = appropriateMethodService;
        this.appropriateTypeService = appropriateTypeService;
    }

    @Override
    public Optional<AppropriateMethod> determining(Update update) {
        return appropriateTypeService.getAppropriateType(update)
                .flatMap(appropriateMethodService::getAppropriateMethod);
    }

}
