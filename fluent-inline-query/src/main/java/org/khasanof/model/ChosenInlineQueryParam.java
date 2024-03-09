package org.khasanof.model;

import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.model
 * @since 3/9/2024 12:51 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChosenInlineQueryParam {

    private String resultId;
    private String query;

}
