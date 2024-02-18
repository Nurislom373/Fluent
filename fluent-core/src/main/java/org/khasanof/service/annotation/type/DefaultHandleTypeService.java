package org.khasanof.service.annotation.type;

import org.khasanof.enums.HandleType;
import org.khasanof.models.handle.HandleTypeFind;
import org.khasanof.registry.handle.HandleTypeFindRegistry;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Nurislom
 * @see org.khasanof.service.annotation.type
 * @since 2/17/2024 11:55 PM
 */
public class DefaultHandleTypeService implements HandleTypeService {

    private final HandleTypeFindRegistry handleTypeFindRegistry;

    public DefaultHandleTypeService(HandleTypeFindRegistry handleTypeFindRegistry) {
        this.handleTypeFindRegistry = handleTypeFindRegistry;
    }

    /**
     *
     * @param update
     * @return
     */
    @Override
    public Optional<HandleType> findByUpdate(Update update) {
        return handleTypeFindRegistry.getHandleTypeFinds().stream()
                .filter(handleTypeFind -> handleTypeFind.getPredicate().test(update))
                .flatMap(handleTypeFind -> getHandleType(update, handleTypeFind))
                .findFirst();
    }

    private Stream<HandleType> getHandleType(Update update, HandleTypeFind handleTypeFind) {
        if (!handleTypeFind.isHasSubServices() || Objects.isNull(handleTypeFind.getHandleTypeFinds())) {
            return Stream.of(handleTypeFind.getHandleType());
        }
        return getHandleTypeInternal(update, handleTypeFind);
    }

    private Stream<HandleType> getHandleTypeInternal(Update update, HandleTypeFind handleTypeFind) {
        return handleTypeFind.getHandleTypeFinds().stream()
                .filter(handleTypeFind1 -> handleTypeFind1.getPredicate().test(update))
                .map(HandleTypeFind::getHandleType);
    }

    /**
     *
     * @param update
     * @return
     */
    @Override
    public List<HandleType> findAllByUpdate(Update update) {
        List<HandleType> handleTypes = new ArrayList<>();
        findAllByUpdateInternal(update, handleTypes);
        return handleTypes;
    }

    private void findAllByUpdateInternal(Update update, List<HandleType> handleTypes) {
        handleTypeFindRegistry.getHandleTypeFinds()
                .stream()
                .filter(find -> find.getPredicate().test(update))
                .forEach(find -> {
                    handleTypes.add(find.getHandleType());
                    if (!find.isHasSubServices() || Objects.isNull(find.getHandleTypeFinds())) {
                        return;
                    }
                    iterateHandleTypeInternal(update, find, handleTypes);
                });
    }

    private void iterateHandleTypeInternal(Update update, HandleTypeFind handleTypeFind, List<HandleType> handleTypes) {
        handleTypeFind.getHandleTypeFinds()
                .stream()
                .filter(handleTypeFind1 -> handleTypeFind1.getPredicate().test(update))
                .forEach(find -> handleTypes.add(find.getHandleType()));
    }
}
