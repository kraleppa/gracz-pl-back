package pl.kraleppa.model.dictionary;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Genre {
    ACTION,
    RPG,
    ADVENTURE,
    SHOOTING,
    STRATEGY,
    SIMULATOR,
    CASUAL,
    SPORTS,
    ARCADE,
    RACING,
    HORROR,
    MMO,
    FIGHTING;

    @JsonCreator
    public static Genre toEnum(String value) {
        return Genre.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toJson(){
        return this.toString();
    }
}
