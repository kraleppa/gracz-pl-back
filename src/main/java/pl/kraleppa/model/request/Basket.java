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
    private double totalPrice;

    public Basket(List<Game> gameList) {
        this.gameList = gameList;
        this.totalPrice = gameList.stream().map(Game::getPrice).reduce(0.0, Double::sum);
        this.totalPrice = Math.round(this.totalPrice * 100.0) / 100.0;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
        this.totalPrice = gameList.stream().map(Game::getPrice).reduce(0.0, Double::sum);
        this.totalPrice = Math.round(this.totalPrice * 100.0) / 100.0;
    }
}
