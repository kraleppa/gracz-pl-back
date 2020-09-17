package pl.kraleppa.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;
import pl.kraleppa.model.dictionary.OrderState;
import pl.kraleppa.model.request.Basket;
import pl.kraleppa.model.request.OrderOptions;

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
    private String paymentOption;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="USER_FK")
    private MyUser user;

    public Order(Basket basket, OrderOptions orderOptions){
        this.orderPrice = basket.getTotalPrice();

        this.shipping = orderOptions.getShipping();
        this.shippingPrice = orderOptions.getShippingPrice();
        this.paymentOption = orderOptions.getPaymentOption();

        this.creationDate = LocalDateTime.now();
        this.orderState = OrderState.NEW;

        this.orderedGames = new ArrayList<>(basket.getGameList());
        basket.getGameList().forEach(game -> game.getOrderList().add(this));
    }
}
