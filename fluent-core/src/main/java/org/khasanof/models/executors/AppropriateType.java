package org.khasanof.models.executors;

import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.models.executors
 * @since 12/24/2023 8:17 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AppropriateType {

    private UpdateType type;
    private Object value;
    private boolean hasSubMethods;

    public AppropriateType(UpdateType type, Object value) {
        this.type = type;
        this.value = value;
    }
}
