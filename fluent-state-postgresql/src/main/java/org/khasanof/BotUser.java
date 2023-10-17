package org.khasanof;

import jakarta.persistence.*;
import lombok.*;
import org.khasanof.state.State;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 10/17/2023 9:46 PM
 */
@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BotUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "first_name")
    private String fistName;

    @Basic(optional = true)
    private String username;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Convert(converter = StateConverter.class)
    private State state;

}
