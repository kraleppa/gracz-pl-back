package pl.kraleppa.config.converter;

import org.springframework.core.convert.converter.Converter;
import pl.kraleppa.model.dictionary.Console;

public class ConsoleEnumConverter implements Converter<String, Console> {
    @Override
    public Console convert(String source) {
        return Console.valueOf(source.toUpperCase());
    }
}
