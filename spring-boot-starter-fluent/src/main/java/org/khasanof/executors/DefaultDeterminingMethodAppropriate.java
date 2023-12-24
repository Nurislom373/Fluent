package org.khasanof.executors;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.khasanof.enums.HandleType;
import org.khasanof.executors.matcher.determining.DeterminingMethodAppropriate;
import org.khasanof.models.executors.AppropriateMethod;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author Nurislom
 * @see org.khasanof.executors
 * @since 12/24/2023 4:53 PM
 */
@Slf4j
@Service
public final class DefaultDeterminingMethodAppropriate implements DeterminingMethodAppropriate {

    private final Function<Update, Optional<MMatchFunctions.MatchType>> function = MMatchFunctions.MatchType::getMatchTypeFunc;

    @Override
    public Optional<AppropriateMethod> determining(Update update) {
        return function.apply(update)
                .flatMap(matchType -> getAppropriateMethod(update, matchType));
    }

    private Optional<AppropriateMethod> getAppropriateMethod(Update update, MMatchFunctions.MatchType matchType) {
        if (!matchType.isHasSubFunctions()) {
            System.out.println("No Sub Functions : " + matchType);
            return Optional.of(createAppropriate(matchType.getSupplyMethod().apply(update)));
        } else {
            System.out.println("Yes Sub Functions : " + matchType);
            return getSubAppropriateMethod(update, matchType);
        }
    }

    @NotNull
    private Optional<AppropriateMethod> getSubAppropriateMethod(Update update, MMatchFunctions.MatchType matchType) {
        return MMatchFunctions.getMatchTypeFunctions(matchType)
                .stream().filter(matchFun -> matchFun.apply(update).supplierEntry().getKey())
                .map(updateRecordFunctionFunction -> updateRecordFunctionFunction.apply(update)
                        .supplierEntry().getValue().get())
                .map(this::createAppropriate)
                .findFirst();
    }

    private AppropriateMethod createAppropriate(Map.Entry<HandleType, Object> entry) {
        return new AppropriateMethod(entry.getKey(), entry.getValue());
    }

}
