package org.khasanof.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.entity
 * @since 3/2/2024 12:26 AM
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flt_state")
public class FltStateEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "state")
    private String state;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private FltUserEntity user;
}
