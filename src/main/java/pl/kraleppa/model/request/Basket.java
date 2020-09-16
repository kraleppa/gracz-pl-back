package pl.kraleppa.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kraleppa.model.entity.Game;

import java.util.List;

@Getter
public class Basket {
    private List<Game> gameList;
    private int numberOfElements;

    public Basket(List<Game> gameList) {
        this.gameList = gameList;
        this.numberOfElements = gameList.size();
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
        this.numberOfElements = gameList.size();
    }
}
