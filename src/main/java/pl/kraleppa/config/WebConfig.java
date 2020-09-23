package pl.kraleppa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.kraleppa.config.converter.ConsoleEnumConverter;
import pl.kraleppa.config.converter.GenreEnumConverter;
import pl.kraleppa.config.converter.OrderStateEnumConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ConsoleEnumConverter());
        registry.addConverter(new GenreEnumConverter());
        registry.addConverter(new OrderStateEnumConverter());
    }
}
