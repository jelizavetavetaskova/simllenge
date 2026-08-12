package dev.vulpden.simllenge.user.model;

import dev.vulpden.simllenge.run.model.Run;
import dev.vulpden.simllenge.user.model.enums.Authority;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "authority")
    @Enumerated(EnumType.STRING)
    private Authority authority = Authority.USER;

    @OneToMany(mappedBy = "user")
    private Set<Run> runs;
}
