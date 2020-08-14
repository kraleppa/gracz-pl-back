package pl.kraleppa.config.converter;

import org.springframework.core.convert.converter.Converter;
import pl.kraleppa.model.dictionary.Genre;

public class GenreEnumConverter implements Converter<String, Genre> {
    @Override
    public Genre convert(String source) {
        return Genre.valueOf(source.toUpperCase());
    }
}
