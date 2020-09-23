package pl.kraleppa.config.converter;

import org.springframework.core.convert.converter.Converter;
import pl.kraleppa.model.dictionary.Genre;
import pl.kraleppa.model.dictionary.OrderState;

public class OrderStateEnumConverter implements Converter<String, OrderState> {
    @Override
    public OrderState convert(String source) {
        return OrderState.valueOf(source.toUpperCase());
    }
}
