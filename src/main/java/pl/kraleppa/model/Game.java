package pl.kraleppa.model;

import com.sun.istack.NotNull;
import lombok.*;
import pl.kraleppa.model.dictionary.Console;
import pl.kraleppa.model.dictionary.Genre;

import javax.persistence.*;

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


    public Game(String name, double price, Genre genre, Console console, String description, String imageUrl) {
        this.name = name;
        this.price = price;
        this.genre = genre;
        this.console = console;
        this.description = description;
        this.imageUrl = imageUrl;
    }
}
