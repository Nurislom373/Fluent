package org.khasanof.table;

import lombok.*;

import java.time.Instant;

/**
 * @author Nurislom
 * @see org.khasanof.table
 * @since 10/27/2023 10:20 PM
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FltStateTable {
    private Long id;
    private String state;
    private String userId;
    private Instant createdAt;
    private Instant updatedAt;
}
