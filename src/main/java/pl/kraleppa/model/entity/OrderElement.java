package pl.kraleppa.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OrderElement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long elementId;

    @NotNull
    private String name;

    @NotNull
    private Double price;

    @NotNull
    private String imageUrl;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ORDER_FK")
    private Order order;

    public OrderElement(Game game, Order order){
        this.name = game.getName();
        this.price = game.getPrice();
        this.imageUrl = game.getImageUrl();
        this.order = order;
    }
}
