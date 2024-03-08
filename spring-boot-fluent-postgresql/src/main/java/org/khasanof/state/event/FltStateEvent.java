package org.khasanof.state.event;

import lombok.*;
import org.khasanof.state.FltState;

/**
 * @author Nurislom
 * @see org.khasanof.state.event
 * @since 3/2/2024 12:13 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FltStateEvent {

    private FltState state;

}
