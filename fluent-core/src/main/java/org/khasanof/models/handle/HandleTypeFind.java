package org.khasanof.models.handle;

import lombok.*;
import org.khasanof.enums.HandleType;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Nurislom
 * @see org.khasanof.service.annotation.type
 * @since 2/18/2024 7:05 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HandleTypeFind {

    private HandleType handleType;
    private boolean hasSubServices;
    private Predicate<Update> predicate;
    private List<HandleTypeFind> handleTypeFinds;

}
