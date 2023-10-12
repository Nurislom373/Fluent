package org.khasanof.executors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.khasanof.enums.HandleType;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Nurislom
 * @see org.khasanof.executors
 * @since 27.06.2023 21:52
 */
@Getter
@RequiredArgsConstructor
public enum MatchFunctions {

    M_HS_TEXT((update -> setFunction(update.getMessage(),
            Message::hasText, Message::getText, HandleType.MESSAGE)), MatchType.MESSAGE),

    M_HS_ANIMATION((update -> setFunction(update.getMessage(),
            Message::hasAnimation, Message::getAnimation, HandleType.MESSAGE)), MatchType.MESSAGE),

    M_HS_STICKER((update -> setFunction(update.getMessage(),
            Message::hasSticker, Message::getSticker, HandleType.STICKER)), MatchType.MESSAGE),

    M_HS_DOCUMENT((update -> setFunction(update.getMessage(),
            Message::hasDocument, Message::getDocument, HandleType.DOCUMENT)), MatchType.MESSAGE),

    M_HS_PHOTO((update -> setFunction(update, update1 -> update1.getMessage().hasPhoto(),
            Update::getMessage, HandleType.PHOTO)), MatchType.MESSAGE),

    M_HS_AUDIO((update -> setFunction(update.getMessage(),
            Message::hasAudio, Message::getAudio, HandleType.AUDIO)), MatchType.MESSAGE),

    M_HS_LOCATION((update -> setFunction(update.getMessage(),
            Message::hasLocation, Message::getLocation, HandleType.LOCATION)), MatchType.MESSAGE),

    M_HS_CONTACT((update -> setFunction(update.getMessage(),
            Message::hasContact, Message::getContact, HandleType.CONTACT)), MatchType.MESSAGE),

    M_HS_POLL((update -> setFunction(update.getMessage(),
            Message::hasPoll, Message::getPoll, HandleType.POLL)), MatchType.MESSAGE),

    M_HS_VIDEO_NOTE((update -> setFunction(update.getMessage(),
            Message::hasVideoNote, Message::getVideoNote, HandleType.VIDEO_NOTE)), MatchType.MESSAGE),

    M_HS_VOICE((update -> setFunction(update.getMessage(),
            Message::hasVoice, Message::getVoice, HandleType.VOICE)), MatchType.MESSAGE),

    M_HS_VIDEO((update -> setFunction(update.getMessage(),
            Message::hasVideo, Message::getVideo, HandleType.VIDEO)), MatchType.MESSAGE);


    private final Function<Update, RecordFunction> method;
    private final MatchType matchType;

    public static Set<Function<Update, RecordFunction>> getMatchTypeFunctions(MatchType type) {
        return Arrays.stream(values()).filter(matchFunctions -> matchFunctions.matchType.equals(type))
                .map(MatchFunctions::getMethod).collect(Collectors.toSet());
    }

    public static <T> RecordFunction setFunction(T message, Function<T, Boolean> booleanFunction, Function<T, Object> objectFunction,
                                                 HandleType type) {
        return new RecordFunction(Map.entry(booleanFunction.apply(message),
                () -> Map.entry(type, objectFunction.apply(message))));
    }

    @Getter
    @AllArgsConstructor
    @RequiredArgsConstructor
    enum MatchType {

        MESSAGE(Update::hasMessage, true),
        INLINE_QUERY(Update::hasInlineQuery, false,
                (update -> setSupplyMethod(update.getInlineQuery(), HandleType.INLINE_QUERY))),
        CALLBACK(Update::hasCallbackQuery, false,
                (update -> setSupplyMethod(update.getCallbackQuery().getData(), HandleType.CALLBACK)));

        private final Function<Update, Boolean> method;
        private final boolean hasSubFunctions;
        private Function<Update, Map.Entry<HandleType, Object>> supplyMethod;

        public static MatchType getMatchType(Update update) {
            return Arrays.stream(values()).filter(matchType -> matchType.method.apply(update))
                    .findFirst().orElse(null);
        }

        public static <T> Map.Entry<HandleType, Object> setSupplyMethod(T data, HandleType type) {
            return Map.entry(type, data);
        }

    }

}
