package org.khasanof.models.executors;

import lombok.*;
import org.khasanof.enums.HandleType;

/**
 * @author Nurislom
 * @see org.khasanof.models.executors
 * @since 12/24/2023 4:51 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AppropriateMethod {

    private HandleType handleType;
    private Object value;

}
