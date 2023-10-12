package org.khasanof.executors;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.enums.HandleType;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author Nurislom
 * @see org.khasanof.executors
 * @since 27.06.2023 21:32
 */
@Slf4j
@Component(HandleFunctionsMatcher.NAME)
public class HandleFunctionsMatcher {

    public static final String NAME = "handleAnyFunctionMatcher";
    private final Function<Update, MatchFunctions.MatchType> function = MatchFunctions.MatchType::getMatchType;

    public Optional<Map.Entry<HandleType, Object>> matchFunctions(Update update) {
        MatchFunctions.MatchType matchType = function.apply(update);
        System.out.println("MatchType function result : " + matchType);
        if (Objects.nonNull(matchType)) {
            if (!matchType.isHasSubFunctions()) {
                System.out.println("No Sub Functions : " + matchType);
                return Optional.ofNullable(matchType.getSupplyMethod().apply(update));
            } else {
                System.out.println("Yes Sub Functions : " + matchType);
                return MatchFunctions.getMatchTypeFunctions(matchType)
                        .stream().filter(matchFun -> matchFun.apply(update).supplierEntry().getKey())
                        .map(updateRecordFunctionFunction -> updateRecordFunctionFunction.apply(update)
                                .supplierEntry().getValue().get())
                        .findFirst();
            }
        }
        return Optional.empty();
    }

}
