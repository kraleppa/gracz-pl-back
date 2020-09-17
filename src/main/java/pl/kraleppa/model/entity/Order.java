package pl.kraleppa.model.entity;

import com.sun.istack.NotNull;
import lombok.*;
import pl.kraleppa.model.dictionary.OrderState;
import pl.kraleppa.model.request.Basket;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
@Entity(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @NotNull
    private double orderPrice;

    @NotNull
    private double shippingPrice;

    @NotNull
    private String shipping;

    @NotNull
    private LocalDateTime creationDate;

    private LocalDateTime payDate;

    private LocalDateTime sendDate;

    private LocalDateTime completionDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @ManyToMany(mappedBy = "orderList", fetch = FetchType.EAGER)
    private List<Game> orderedGames;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="USER_FK")
    private MyUser user;

    public Order(Basket basket){
        this.orderPrice = basket.getTotalPrice();

        this.shipping = "NIC";
        this.shippingPrice = 0.0;

        this.creationDate = LocalDateTime.now();
        this.orderState = OrderState.NEW;

        this.orderedGames = new ArrayList<>(basket.getGameList());
        basket.getGameList().forEach(game -> game.getOrderList().add(this));
    }
}
