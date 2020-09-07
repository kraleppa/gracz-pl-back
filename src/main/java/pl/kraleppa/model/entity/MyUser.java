package pl.kraleppa.model.entity;

import com.sun.istack.NotNull;
import lombok.*;
import pl.kraleppa.model.dictionary.Role;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String userName;

    @NotNull
    private String password;

    private boolean active;

    @Enumerated(EnumType.STRING)
    private Role role;
}
