package org.khasanof.entity;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * @author Nurislom
 * @see org.khasanof.entity
 * @since 3/16/2024 7:54 PM
 */
@Getter
@Setter
@ToString
@RedisHash("state")
@NoArgsConstructor
@AllArgsConstructor
public class FltStateHash implements Serializable {

    private Long id;
    private String state;
    private Long userId;

}
