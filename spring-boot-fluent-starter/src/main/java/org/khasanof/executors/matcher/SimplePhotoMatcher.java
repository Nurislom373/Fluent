package org.khasanof.executors.matcher;

import org.khasanof.annotation.methods.HandlePhoto;
import org.khasanof.condition.Condition;
import org.khasanof.config.ApplicationConstants;
import org.khasanof.enums.scopes.PhotoScope;
import org.khasanof.executors.expression.ExpressionMatcher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Nurislom
 * @see org.khasanof.executors.matcher
 * @since 06.07.2023 22:37
 */
@Component
public class SimplePhotoMatcher extends GenericMatcher<HandlePhoto, Message> {

    private final Map<PhotoScope, Function<List<PhotoSize>, Object>> biFunctionMap = new HashMap<>();
    private final Map<PhotoScope, Function<Message, Object>> messageFunctionMap = new HashMap<>();

    {
        setBiFunctionMap();
    }

    public SimplePhotoMatcher() {
        super(ApplicationConstants.MATCHER_MAP);
    }

    @Override
    public boolean matcher(HandlePhoto annotation, Message value) {
        Object apply = getApplyValue(annotation, value);
        return matchFunctions.get(Map.entry(annotation.match(), getScopeType(apply, annotation.match())))
                .apply(annotation.value(), apply);
    }

    private Object getApplyValue(HandlePhoto annotation, Message value) {
        return Condition.orElse(annotation.scope().isMessageType(),
                () -> messageFunctionMap.get(annotation.scope()).apply(value),
                () -> biFunctionMap.get(annotation.scope()).apply(value.getPhoto()));
    }

    @Override
    public Class<HandlePhoto> getType() {
        return HandlePhoto.class;
    }

    void setBiFunctionMap() {
        messageFunctionMap.put(PhotoScope.CAPTION, (Message::getCaption));
        biFunctionMap.put(PhotoScope.HEIGHT, (list) -> list.get(list.size() - 1).getHeight());
        biFunctionMap.put(PhotoScope.WIDTH, (list) -> list.get(list.size() - 1).getWidth());
        biFunctionMap.put(PhotoScope.FILE_SIZE, (list) -> list.get(list.size() - 1).getFileSize());
    }
}
