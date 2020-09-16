package pl.kraleppa.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import pl.kraleppa.model.dictionary.Console;
import pl.kraleppa.model.dictionary.Genre;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gameId;

    @NotNull
    private String name;

    @NotNull
    private Double price;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    private Console console;

    @NotNull
    private String description;

    @NotNull
    private String imageUrl;

    @JsonIgnore
    @ManyToMany(fetch=FetchType.EAGER)
    private List<MyUser> userList;
}
