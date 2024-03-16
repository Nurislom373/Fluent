package org.khasanof.entity;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 3/16/2024 6:29 PM
 */
@Getter
@Setter
@ToString
@RedisHash("User")
@NoArgsConstructor
@AllArgsConstructor
public class FltUserHash implements Serializable {

    private Long id;
    private Long userId;
    private String firstname;
    private String lastname;
    private String username;
    private String languageCode;
    private Boolean supportInlineQueries;
    private Long stateId;

}
