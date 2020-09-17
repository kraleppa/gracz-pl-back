package pl.kraleppa.model.request;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderOptions {
    private double shippingPrice;
    private String shipping;
    private String paymentOption;
}
