package pl.kraleppa.model.dictionary;

import antlr.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Console {
    PLAYSTATION_4,
    XBOX_ONE,
    NINTENDO_SWITCH;

    @JsonCreator
    public static Console toEnum(String value) {
        return Console.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toJson(){
        return this.toString();
    }
}
