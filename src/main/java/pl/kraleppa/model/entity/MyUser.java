package pl.kraleppa.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;
import pl.kraleppa.model.dictionary.Role;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
@Entity
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique=true)
    private String username;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    @Column(unique=true)
    private String email;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String address;

    @NotNull
    private String city;

    @NotNull
    private String zip;

    @JsonIgnore
    @ManyToMany(mappedBy = "userList", fetch=FetchType.EAGER)
    private List<Game> basket;

    public void insertGameToBasket(Game game){
        this.basket.add(game);
        game.getUserList().add(this);
    }

    public void deleteGameFromBasket(Game game){
        this.setBasket(this.basket.stream().filter(g -> !g.getGameId().equals(game.getGameId())).collect(Collectors.toList()));
        game.setUserList(game.getUserList().stream().filter(u -> !u.id.equals(this.id)).collect(Collectors.toList()));
    }
}
