package org.khasanof.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.entity
 * @since 3/2/2024 12:25 AM
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flt_user")
public class FltUserEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "username")
    private String username;

    @Column(name = "language_code")
    private String languageCode;

    @Column(name = "support_inline-queries")
    private Boolean supportInlineQueries;

    @OneToOne(mappedBy = "user")
    private FltStateEntity state;
}
